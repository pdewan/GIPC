package consensus.asynchronous;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import port.trace.consensus.ProposalAcceptedNotificationReceived;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalLearnedNotificationReceived;
import port.trace.consensus.ProposalQuorumAchieved;
import port.trace.consensus.ProposalQuorumNotAchieved;
import sessionport.rpc.group.GroupRPCSessionPort;
import consensus.AnAbstractConsensusMechanism;
import consensus.ReplicationSynchrony;
import consensus.Learned;
import consensus.Learner;
import consensus.ProposalState;
import consensus.ProposalRejectionKind;

public class AnAsynchronousConsensusMechanism<StateType> extends
		ALearnerMechanism<StateType> {
	protected Learner<StateType> learners;
//	protected short numLearners;

	public AnAsynchronousConsensusMechanism(
			GroupRPCSessionPort anInputPort, String aName, short aMyId,
			Learner<StateType> aPeerProxy) {
		super(anInputPort, aName, aMyId);
		learners = aPeerProxy;
//		numLearners = aNumLearners;
		// eventualConsistency = anEventualConsistency;
	}

	protected Learner<StateType> learners() {
		return learners;
	}
	
	protected short maxLearners() {
		return numMaximumMembers();
	}
	
	protected short numCurrentLearners() {
		return numCurrentMembers();
	}
	protected void recordSentLearnNotification(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind) {
		
	}
	protected void sendLearnNotification(float aProposalNumber,
			StateType aProposal, ProposalRejectionKind anAgreement) {
		ProposalLearnNotificationSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal, anAgreement);
		learners().learn(aProposalNumber, aProposal, anAgreement);

	}
//	protected void sendLearnNotificationToOthers(float aProposalNumber,
//			StateType aProposal, ProposalVetoKind anAgreement) {
//		sendLearnNotification(aProposalNumber, aProposal, anAgreement);
//
//	}
	protected boolean sufficientLearners(short aMaxLearners, short aCurrentLearners) {
		return aMaxLearners == aCurrentLearners;
	}
//	protected void recordReceivedLearnNotification(float aProposalNumber, StateType aProposal, ProposalVetoKind anAgreement) {
//		if (!isEventualConsistency() && learnedByTimeout()) {
//			waitForReceipt(aProposalNumber, aProposal);			
//		}
//		super.recordReceivedLearnNotification(aProposalNumber, aProposal, anAgreement);
//	}
	protected Boolean sufficientLearners(short aMaxLearners, short aCurrentLearners, short aLearnedNotifications) {
		if (aLearnedNotifications != aCurrentLearners)
			return null;
		if (aCurrentLearners >= aMaxLearners/2) {
			return true;	
		}
		return false;
	}
	@Override
	protected void propose(float aProposalNumber, StateType aProposal) {
		ProposalRejectionKind aRejectionKind = ProposalRejectionKind.ACCEPTED;
		recordSentLearnNotification(aProposalNumber, aProposal, aRejectionKind);
		sendLearnNotification(aProposalNumber, aProposal, aRejectionKind);
	}
	

//	@Override
//	public void learned(float aProposalNumber, StateType aProposal) {
//		ProposalLearnedNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
//		if (!isPending(aProposalNumber))
//			return;
//		short aTotalLearners = maxLearners();
//		short aLearnedNotifications = incrementCount(aProposalNumber, LEARNED_NOTIFICATION, 1);
//		Boolean aSufficientLearners = sufficientLearners(aTotalLearners, numCurrentLearners(), aLearnedNotifications);
//		if (aSufficientLearners == null) {
//			return;
//		}
//		if (aSufficientLearners) {
//			ProposalQuorumAchieved.newCase(this, getObjectName(), aProposalNumber, aProposal, aTotalLearners, aLearnedNotifications);
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
//		} else {
//			ProposalQuorumNotAchieved.newCase(this, getObjectName(), aProposalNumber, aProposal, aTotalLearners, aLearnedNotifications);
//
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_NOT_COMMUNICATED);
//
//		}
//		
//		
//	}

}
