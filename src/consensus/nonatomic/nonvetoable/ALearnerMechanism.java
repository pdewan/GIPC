package consensus.nonatomic.nonvetoable;

import inputport.InputPort;
import port.trace.consensus.ProposalLearnNotificationReceived;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learner;
import consensus.ProposalState;
import consensus.ProposalVetoKind;

public class ALearnerMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements Learner<StateType> {
	public ALearnerMechanism(InputPort anInputPort, 
			String aName, short aMyId
			) {
		super(anInputPort, aName, aMyId);
	}
//	protected Learned<StateType> proposer() {
//		return proposer;
//	}
	protected void recordReceivedLearnNotification(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind) {
		recordProposal(aProposalNumber, aProposal);
		if (isAgreement(aVetoKind))
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		else
			newProposalState(aProposalNumber, aProposal,toProposalState(aVetoKind));
	}
	@Override
	public synchronized void learn(float aProposalNumber, StateType aProposal, ProposalVetoKind aVetoKind) {
		ProposalLearnNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal, aVetoKind);
//		recordProposal(aProposalNumber, aProposal);
		recordReceivedLearnNotification(aProposalNumber, aProposal, aVetoKind);

//		if (isAgreement(anAgreement))
//			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
//		else
//			newProposalState(aProposalNumber, aProposal,toProposalState(anAgreement));
		
	}
//	protected void setLearnedState(float aProposalNumber, StateType aProposal, ProposalVetoKind anAgreement) {
//		if (!eventualConsistency() && learnedByTimeout()) {
//			waitForReceipt(aProposalNumber, aProposal);			
//		}
//		super.setLearnedState(aProposalNumber, aProposal, anAgreement);
//	}
//	@Override
//	public void learn(float aProposalNumber, StateType aProposal, ProposalVetoKind anAgreement) {
//		super.learn(aProposalNumber, aProposal, anAgreement);
//		if (eventualConsistency() && learnedByTimeout()) {
//			return;
//		}
//		proposer().learned(aProposalNumber, aProposal);		
//	}
	


	



	


}
