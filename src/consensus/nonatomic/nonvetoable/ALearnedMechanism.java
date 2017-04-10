package consensus.nonatomic.nonvetoable;

import inputport.ConnectionRegistrar;
import port.trace.consensus.ProposalAcceptedNotificationReceived;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalLearnedNotificationReceived;
import consensus.AnAbstractConsensusMechanism;
import consensus.ConsensusSynchrony;
import consensus.Learner;
import consensus.ProposalState;
import consensus.ProposalVetoKind;

public class ALearnedMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements LearnedMechanism<StateType> {
	protected Learner<StateType> learners;
//	protected short numLearners;

	public ALearnedMechanism(
			ConnectionRegistrar anInputPort, String aName, short aMyId,
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

	protected void sendLearnNotification(float aProposalNumber,
			StateType aProposal, ProposalVetoKind anAgreement) {
		ProposalLearnNotificationSent.newCase(this, getObjectName(),
				aProposalNumber, aProposal, anAgreement);
		learners().learn(aProposalNumber, aProposal, anAgreement);

	}
	protected void sendLearnNotificationToOthers(float aProposalNumber,
			StateType aProposal, ProposalVetoKind anAgreement) {
		sendLearnNotification(aProposalNumber, aProposal, anAgreement);

	}
	protected boolean sufficientLearners(short aMaxLearners, short aCurrentLearners) {
		return aMaxLearners == aCurrentLearners;
	}
	protected Boolean sufficientLearners(short aMaxLearners, short aCurrentLearners, short aLearnedNotifications) {
		if (aMaxLearners != aCurrentLearners)
			return false;
		if (aLearnedNotifications != aCurrentLearners)
			return null;
		return true;
	}
	@Override
	protected void propose(float aProposalNumber, StateType aProposal) {
		sendLearnNotification(aProposalNumber, aProposal, ProposalVetoKind.NO_VETO);
		if (eventualConsistency() && learnedByTimeout()) {
			waitForReceipt();			
			if (sufficientLearners(maxLearners(), numCurrentLearners())) {
				newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
			} else {
				newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_NOT_COMMUNICATED);
			}
		}
	}
	

	@Override
	public void learned(float aProposalNumber, StateType aProposal) {
		ProposalLearnedNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal);
		if (!isPending(aProposalNumber))
			return;
		short aLearnedNotifications = incrementCount(aProposalNumber, LEARNED_NOTIFICATION, 1);
		Boolean aSufficientLearners = sufficientLearners(maxLearners(), numCurrentLearners(), aLearnedNotifications);
		if (aSufficientLearners == null) {
			return;
		}
		if (aSufficientLearners) {
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		} else {
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_NOT_COMMUNICATED);

		}
		
		
	}

}
