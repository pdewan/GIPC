package consensus.asynchronous.sequential;

import consensus.AnAbstractConsensusMechanism;
import consensus.ProposalFeedbackKind;
import consensus.ProposalState;
import sessionport.rpc.group.GIPCSessionRegistry;
import util.trace.port.consensus.ProposalLearnNotificationReceived;

public class ALearnerConsensusMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements Learner<StateType> {
	protected float maxProposalNumberReceivedInLearnNotification = -1;

	public ALearnerConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
	}

	// protected Learned<StateType> proposer() {
	// return proposer;
	// }
	protected ProposalState toProposalState(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		return toProposalState(aFeedbackKind);
	}

	protected void recordReceivedLearnNotification(float aProposalNumber,
			StateType aProposal, ProposalFeedbackKind aFeedbackKind) {
		recordProposalState(aProposalNumber, aProposal);
		if (!isPending(aProposalNumber))
			return;
		maxProposalNumberReceivedInLearnNotification = Math.max(
				maxProposalNumberReceivedInLearnNotification, aProposalNumber);		
		newProposalState(aProposalNumber, aProposal,
				toProposalState(aProposalNumber, aProposal, aFeedbackKind));
	}
	public synchronized void localLearn(float aProposalNumber, StateType aProposal,
			ProposalFeedbackKind aRejectionKind) {
		
		if (isValueSynchrony()) {
			waitForReceipt(aProposalNumber, aProposal);
		}
		recordReceivedLearnNotification(aProposalNumber, aProposal,
				aRejectionKind);
	}

	@Override
	public synchronized void learn(float aProposalNumber, StateType aProposal,
			ProposalFeedbackKind aRejectionKind) {
		ProposalLearnNotificationReceived.newCase(this, getObjectName(),
				aProposalNumber, aProposal, aRejectionKind);
		localLearn(aProposalNumber, aProposal, aRejectionKind);
//		if (isValueSynchrony()) {
//			waitForReceipt(aProposalNumber, aProposal);
//		}
//		recordReceivedLearnNotification(aProposalNumber, aProposal,
//				aRejectionKind);
	}
	// protected void setLearnedState(float aProposalNumber, StateType
	// aProposal, ProposalVetoKind anAgreement) {
	// if (!eventualConsistency() && learnedByTimeout()) {
	// waitForReceipt(aProposalNumber, aProposal);
	// }
	// super.setLearnedState(aProposalNumber, aProposal, anAgreement);
	// }
	// @Override
	// public void learn(float aProposalNumber, StateType aProposal,
	// ProposalVetoKind anAgreement) {
	// super.learn(aProposalNumber, aProposal, anAgreement);
	// if (eventualConsistency() && learnedByTimeout()) {
	// return;
	// }
	// proposer().learned(aProposalNumber, aProposal);
	// }

}
