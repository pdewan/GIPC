package consensus.nonatomic.nonvetoable;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import port.trace.consensus.ProposalAcceptedNotificationReceived;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalLearnedNotificationReceived;
import port.trace.consensus.ProposalQuorumAchieved;
import port.trace.consensus.ProposalQuorumNotAchieved;
import consensus.AnAbstractConsensusMechanism;
import consensus.ConsensusSynchrony;
import consensus.Learned;
import consensus.Learner;
import consensus.ProposalState;
import consensus.ProposalVetoKind;

public class AnAsynchronousProposerAndLearnerMechanism<StateType> extends
		ALearnerMechanism<StateType> {
	protected Learner<StateType> learners;
//	protected short numLearners;

	public AnAsynchronousProposerAndLearnerMechanism(
			InputPort anInputPort, String aName, short aMyId,
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
		return maxPeers();
	}
	
	protected short numCurrentLearners() {
		return numCurrentPeers();
	}
	protected void recordSentLearnNotification(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind) {
		
	}
	protected void sendLearnNotification(float aProposalNumber,
			StateType aProposal, ProposalVetoKind anAgreement) {
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
		ProposalVetoKind aVetoKind = ProposalVetoKind.NO_VETO;
		recordSentLearnNotification(aProposalNumber, aProposal, aVetoKind);
		sendLearnNotification(aProposalNumber, aProposal, aVetoKind);
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
