package consensus.synchronous;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import inputport.datacomm.simplex.buffer.nio.AnAcceptCommand;
import inputport.rpc.duplex.MaybeProcessReturnValue;
import port.trace.consensus.SufficientAgreementsChecked;
import port.trace.consensus.ProposalAcceptRequestReceived;
import port.trace.consensus.ProposalAcceptRequestSent;
import port.trace.consensus.ProposalAcceptResponseReceived;
import port.trace.consensus.ProposalAcceptedNotificationSent;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalPrepareRequestSent;
import port.trace.consensus.ProposalPreparedNotificationReceived;
import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import consensus.ConsensusMemberSetKind;
import consensus.ReplicationSynchrony;
import consensus.ProposalState;
import consensus.ProposalFeedbackKind;
import consensus.asynchronous.AnAsynchronousConsensusMechanism;

public class ASynchronousConsensusMechanism<StateType> extends
		AnAcceptorConsensusMechanism<StateType> implements Accepted<StateType> {

	protected float maxProposalNumberSentInAcceptRequest = -1;
	protected float maxProposalNumberReceivedInAcceptedNotification = -1;
	protected float maxProposalNumberReceivedInSuccessfulAcceptedNotification = -1;

	public ASynchronousConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);

	}

	protected Acceptor<StateType> acceptors() {
		return (Acceptor<StateType>) learners();
	}

	protected void recordSentAcceptRequest(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		maxProposalNumberSentInAcceptRequest = Math.max(
				maxProposalNumberSentInAcceptRequest, aProposalNumber);

	}

	protected void recordReceivedAcceptedNotification(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		maxProposalNumberReceivedInAcceptedNotification = Math.max(
				maxProposalNumberReceivedInAcceptedNotification,
				aProposalNumber);
		// maxReceivedAcceptedNotificationProposalNumber =
		// Math.max(maxReceivedAcceptedNotificationProposalNumber,
		// aProposalNumber);
		incrementCount(aProposalNumber, ACCEPT_NOTIFICATION, 1);

		if (isSuccess(aFeedbackKind)) {
			// lastAcceptedProposal = aProposal;
			// lastAcceptedProposalNumber = Math.max(aProposalNumber,
			// lastAcceptedProposalNumber);
			maxProposalNumberReceivedInSuccessfulAcceptedNotification = Math
					.max(maxProposalNumberReceivedInAcceptedNotification,
							aProposalNumber);
			incrementCount(aProposalNumber, ACCEPT_SUCCESS, 1);
		}
	}

	protected void localPropose(float aProposalNumber, StateType aProposal) {
		if (isAsynchronousReplication()) {
			recordAndSendLearnNotification(aProposalNumber, aProposal,
					ProposalFeedbackKind.SUCCESS);
		} else {
			recordAndSendAcceptRequest(aProposalNumber, aProposal);
		}
	}

	protected void recordAndSendAcceptRequest(float aProposalNumber,
			StateType aProposal) {
		recordAcceptRequest(aProposalNumber, aProposal);
		sendAcceptRequest(aProposalNumber, aProposal);
	}

	protected void recordAcceptRequest(float aProposalNumber,
			StateType aProposal) {
		maxProposalNumberSentInAcceptRequest = Math.max(
				maxProposalNumberSentInAcceptRequest, aProposalNumber);

	}

	protected void sendAcceptRequest(float aProposalNumber, StateType aProposal) {
		ProposalAcceptRequestSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal);
		acceptors().accept(aProposalNumber, aProposal);
	}

	protected boolean customSufficientAgrements(float aProposalNumber,
			StateType aProposal, short aMaxAcceptors, short aCurrentAcceptors,
			int anAcceptNotifications, int anAgreements) {
		return true;
	}

	protected Boolean synchronousAgreements(float aProposalNumber,
			StateType aProposal, short aMaxAcceptors, short aCurrentAcceptors,
			int anAcceptNotifications, int anAgreements) {
		return sufficientAgreements(aProposalNumber, aProposal, aMaxAcceptors,
				aCurrentAcceptors, aMaxAcceptors, anAcceptNotifications,
				anAgreements);

	}

	protected Boolean sufficientAgreements(float aProposalNumber,
			StateType aProposal, short aMaxAcceptors, short aCurrentAcceptors,
			float aRequiredAgreements, int anAcceptNotifications,
			int anAgreements) {
		Boolean retVal;
		if (anAgreements >= aRequiredAgreements) {
			retVal = true;
		} else if (aCurrentAcceptors == anAcceptNotifications) {
			retVal = false;
		} else {
			retVal = null;
		}
		SufficientAgreementsChecked.newCase(this, getObjectName(),
				aProposalNumber, aProposal, aMaxAcceptors, aCurrentAcceptors,
				aRequiredAgreements, anAcceptNotifications, anAgreements,
				retVal);
		return retVal;
	}

	protected Boolean majorityAgreements(float aProposalNumber,
			StateType aProposal, short aMaxAcceptors, short aCurrentAcceptors,
			int anAcceptNotifications, int anAgreements) {
		return sufficientAgreements(aProposalNumber, aProposal, aMaxAcceptors,
				aCurrentAcceptors, (float) (((float) aMaxAcceptors) / (2.0)),
				anAcceptNotifications, anAgreements);
	}

	protected Boolean oneAgreement(float aProposalNumber, StateType aProposal,
			short aMaxAcceptors, short aCurrentAcceptors,
			int anAcceptNotifications, int anAgreements) {
		return sufficientAgreements(aProposalNumber, aProposal, aMaxAcceptors,
				aCurrentAcceptors, 1, anAcceptNotifications, anAgreements);
	}

	protected Boolean asynchronousAgreements(float aProposalNumber,
			StateType aProposal, short aMaxAcceptors, short aCurrentAcceptors,
			int anAcceptNotifications, int anAgreements) {
		return true;
	}

	protected Boolean sufficientAgreements(
			ReplicationSynchrony aReplicationSynchrony, float aProposalNumber,
			StateType aProposal, short aMaxAcceptors, short aCurrentAcceptors,
			int anAcceptNotifications, int anAgreements) {

		switch (aReplicationSynchrony) {
		case ALL_SYNCHRONOUS:
			return synchronousAgreements(aProposalNumber, aProposal,
					aMaxAcceptors, aCurrentAcceptors, anAcceptNotifications,
					anAgreements);
		case MAJORITY_SYNCHRONOUS:
			return majorityAgreements(aProposalNumber, aProposal,
					aMaxAcceptors, aCurrentAcceptors, anAcceptNotifications,
					anAgreements);
		case ONE_SYNCHRONOUS:
			return oneAgreement(aProposalNumber, aProposal, aMaxAcceptors,
					aCurrentAcceptors, anAcceptNotifications, anAgreements);
		case ASYNCHRONOUS:
			return asynchronousAgreements(aProposalNumber, aProposal,
					aMaxAcceptors, aCurrentAcceptors, anAcceptNotifications,
					anAgreements);
		case CUSTOM_SYNCHRONOUS:
			return customSufficientAgrements(aProposalNumber, aProposal,
					aMaxAcceptors, aCurrentAcceptors, anAcceptNotifications,
					anAgreements);

		}
		return false;
	}

	private Boolean sufficientAcceptors(float aProposalNumber,
			StateType aProposal) {
		return sufficientAgreements(getAcceptSynchrony(), aProposalNumber,
				aProposal, numConsensusMembers(), numCurrentMembers(),
				getCount(aProposalNumber, ACCEPT_NOTIFICATION),
				getCount(aProposalNumber, ACCEPT_SUCCESS));
	}

	protected short numConsensusMembers() {

		switch (getConsensusMemberSetKind()) {
		case INITIAL_MEMBERS:
			return numInitialMembers();
		case MAXIMUM_MEMBERS:
			return numMaximumMembers();
		case CURRENT_MEMBERS:
			return numCurrentMembers;
		}

		return 0;
	}

	protected void recordNotificationCount(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind,
			String aNotificationKind) {
		if (isSuccess(aFeedbackKind)) {
			incrementCount(aProposalNumber, aNotificationKind, 1);
		}
		;
	}

	protected boolean heardFromAll() {
		return false;
	}

	protected void processAcceptRejection(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {

		if (isSendRejectionNotification()) {
			recordAndSendLearnNotification(aProposalNumber, aProposal,
					aFeedbackKind);
		} else {
			learn(aProposalNumber, aProposal, aFeedbackKind);
		}

	}

	protected void aggregateAcceptedNotification(
			ReplicationSynchrony aReplicationSynchrony, float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		Boolean isSufficientAcceptors = sufficientAcceptors(aProposalNumber,
				aProposal);
		if (isSufficientAcceptors == null)
			return;
		if (isSufficientAcceptors) {
			recordAndSendLearnNotification(aProposalNumber, aProposal,
					ProposalFeedbackKind.SUCCESS);

		} else {
			processAcceptRejection(aProposalNumber, aProposal,
					ProposalFeedbackKind.AGGREGATE_DENIAL);

		}
	}

	@Override
	public void accepted(float aProposalNumber, StateType aProposal,
			ProposalFeedbackKind aFeedbackKind) {
		ProposalAcceptResponseReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal, aFeedbackKind);
		recordReceivedAcceptedNotification(aProposalNumber, aProposal,
				aFeedbackKind);
		if (!isPending(aProposalNumber)) {
			return;
		}
		if (!isSuccess(aFeedbackKind) && isAllSynchronous()) {
			processAcceptRejection(aProposalNumber, aProposal, aFeedbackKind);
			return;
		}
		aggregateAcceptedNotification(getAcceptSynchrony(), aProposalNumber,
				aProposal, aFeedbackKind);
	}

}
