package consensus.paxos;

import java.util.HashMap;
import java.util.Map;

import sessionport.rpc.group.GIPCSessionRegistry;
import trace.port.consensus.ProposalPreparedNotificationReceived;
import consensus.ProposalFeedbackKind;
import consensus.ProposalState;
import consensus.ReplicationSynchrony;

public class APaxosConsensusMechanism<StateType> extends
		APreparerConsensusMechanism<StateType> implements Prepared<StateType> {

	protected Map<Float, Boolean> preparePhaseOver = new HashMap();

	protected float maxProposalNumberSentInPrepareRequest = -1;

	protected float maxProposalNumberReceivedInPreparedNotification;
	protected float maxProposalNumberReceivedInSuccessfulPreparedNotification = -1;

	protected float maxAcceptedProposalNumberReceivedInPreparedNotification;

	public APaxosConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
	}

	protected void setPreparePhaseOver(float aProposalNumber) {
		preparePhaseOver.put(aProposalNumber, true);
	}

	protected boolean isPreparePhaseOver(float aProposalNumber) {
		return (preparePhaseOver.get(aProposalNumber) != null)
				&& preparePhaseOver.get(aProposalNumber);
	}

	protected void recordReceivedPreparedNotification(
			float anAcceptedProposalNumber, StateType anAcceptedProposal,
			float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		maxProposalNumberReceivedInPreparedNotification = Math.max(
				maxProposalNumberReceivedInPreparedNotification,
				aPreparedProposalNumber);
		if (anAcceptedProposal != null) {
			maxAcceptedProposalNumberReceivedInPreparedNotification = Math.max(
					maxAcceptedProposalNumberReceivedInPreparedNotification,
					anAcceptedProposalNumber);
		}
		incrementCount(aPreparedProposalNumber, PREPARE_NOTIFICATION, 1);
	}

	private Boolean sufficientPeparers(
			ReplicationSynchrony aReplicationSynchrony,
			float aPreparedProposalNumber) {
		int aNumPrepareNotifications = getCount(aPreparedProposalNumber,
				PREPARE_NOTIFICATION);
		return sufficientAgreements(aReplicationSynchrony,
				aPreparedProposalNumber, proposal(aPreparedProposalNumber),
				numConsensusMembers(), numCurrentMembers(),
				aNumPrepareNotifications, aNumPrepareNotifications);
	}

	protected StateType preparedProposal(float aPreparedProposalNumber) {
		float aChosenProposalNumber = maxAcceptedProposalNumberReceivedInPreparedNotification <= 0 ? aPreparedProposalNumber
				: maxAcceptedProposalNumberReceivedInPreparedNotification;
		return proposal(aChosenProposalNumber);
	}

	protected void aggregatePreparedNotification(
			float anAcceptedProposalNumber, StateType anAcceptedProposal,
			float aPreparedProposalNumber, ProposalFeedbackKind aFeedbackKind) {
		Boolean isSufficientPreparers = sufficientPeparers(
				getPrepareSynchrony(), aPreparedProposalNumber);
		if (isSufficientPreparers == null)
			return;
		setPreparePhaseOver(aPreparedProposalNumber);
		if (isSufficientPreparers) {
			startAcceptPhase(aPreparedProposalNumber,
					preparedProposal(aPreparedProposalNumber));
		} else {
			newProposalState(
					aPreparedProposalNumber,
					proposal(aPreparedProposalNumber),
					ProposalState.PROPOSAL_AGGREGATE_DENIAL);
			return;
		}

		// recordAndSendAcceptRequest(aPreparedProposalNumber,
		// preparedState(aPreparedProposalNumber));
	}

	protected StateType successProposedState(float anAcceptedProposalNumber,
			StateType anAcceptedProposal, float aPreparedProposalNumber,
			ProposalFeedbackKind aFeedbackKind) {
		return anAcceptedProposal == null ? proposal(aPreparedProposalNumber)
				: anAcceptedProposal;
	}

	protected ProposalState toProposalState(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		ProposalState result = toProposalState(aFeedbackKind);

		return result;
	}

//	protected Preparer<StateType> preparers() {
//		return (Preparer<StateType>) super.all();
//	}

	protected void recordAndSendPrepareRequest(float aProposalNumber,
			StateType aProposal) {
		recordPrepareRequest(aProposalNumber, aProposal);
		sendPrepareRequest(aProposalNumber, aProposal);
	}

	protected void recordPrepareRequest(float aProposalNumber,
			StateType aProposal) {
		maxProposalNumberSentInPrepareRequest = Math.max(
				maxProposalNumberSentInPrepareRequest, aProposalNumber);
	}

	protected void sendPrepareRequest(float aProposalNumber, StateType aProposal) {
		if (!isPrepareInSeparateThread()) {
			preparers().prepare(aProposalNumber, aProposal);
		} else {
			Thread aThread = new Thread(
					new APrepareMulticastRunnable<StateType>(preparers(),
							aProposalNumber, aProposal));
			aThread.setName("Prepare Thread:" + aProposalNumber);
			aThread.start();
		}
	}

	protected void localPropose(float aProposalNumber, StateType aProposal) {
		if (isNotPaxos()) {
			super.localPropose(aProposalNumber, aProposal);
		} else {
			// recordAndSendPrepareRequest(aProposalNumber, aProposal);
			startPreparePhase(aProposalNumber, aProposal);
		}
	}

	protected void startPreparePhase(float aProposalNumber, StateType aProposal) {
		recordAndSendPrepareRequest(aProposalNumber, aProposal);
	}

	protected void startAcceptPhase(float aProposalNumber, StateType aProposal) {
		super.startAcceptPhase(aProposalNumber, aProposal);
	}

	protected void recordReceivedAcceptedNotification(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		super.recordProposalState(aProposalNumber, aProposal); // could be the
																// first time we
																// have seen
																// this proposal
		super.recordReceivedAcceptedNotification(aProposalNumber, aProposal,
				aFeedbackKind);
	}

//	protected void sendAcceptedNotification(float aProposalNumber,
//			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
//		if (isNotPaxos()) {
//			super.sendAcceptedNotification(aProposalNumber, aProposal,
//					aFeedbackKind);
//			return;
//		}
//		sendAcceptedNotificationToLearners(aProposalNumber, aProposal,
//				aFeedbackKind);
//	}
//
//	protected void sendAcceptedNotificationToLearners(float aProposalNumber,
//			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
//		if (!isAcceptedInSeparareThread()) {
//			preparers().accepted(aProposalNumber, aProposal, aFeedbackKind);
//		} else {
//			Thread aThread = new Thread(
//					new AnAcceptedMulticastRunnable<StateType>(preparers(),
//							aProposalNumber, aProposal, aFeedbackKind));
//			aThread.setName("Accepted thread " + aProposalNumber);
//			aThread.start();
//		}
//	}

	@Override
	public void prepared(float aPreparedOrAcceptedProposalNumber,
			StateType anAcceptedProposal, float aPreparedProposalNumber,
			ProposalFeedbackKind aFeedbackKind) {
		ProposalPreparedNotificationReceived.newCase(this, getObjectName(),
				aPreparedOrAcceptedProposalNumber, anAcceptedProposal,
				aPreparedProposalNumber, aFeedbackKind);

		recordReceivedPreparedNotification(aPreparedOrAcceptedProposalNumber,
				anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);
		if (!isPending(aPreparedProposalNumber)
				|| isPreparePhaseOver(aPreparedProposalNumber)) {
			return;
		}
		if (aFeedbackKind == ProposalFeedbackKind.CONCURRENCY_CONFLICT) {
			newProposalState(
					aPreparedProposalNumber,
					proposal(aPreparedProposalNumber),
					toProposalState(aPreparedProposalNumber,
							anAcceptedProposal, aFeedbackKind));
			return;
		}
		aggregatePreparedNotification(aPreparedOrAcceptedProposalNumber,
				anAcceptedProposal, aPreparedProposalNumber, aFeedbackKind);

	}

}
