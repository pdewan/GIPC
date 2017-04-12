package consensus.asynchronous;

import inputport.InputPort;
import port.trace.consensus.ProposalLearnNotificationReceived;
import sessionport.rpc.group.GroupRPCSessionPort;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learner;
import consensus.ProposalState;
import consensus.ProposalRejectionKind;

public class ALearnerMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType> implements Learner<StateType> {
	public ALearnerMechanism(GroupRPCSessionPort anInputPort, 
			String aName, short aMyId
			) {
		super(anInputPort, aName, aMyId);
	}
//	protected Learned<StateType> proposer() {
//		return proposer;
//	}
	protected void recordReceivedLearnNotification(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind) {
		recordProposal(aProposalNumber, aProposal);
		if (isAgreement(aRejectionKind))
			newProposalState(aProposalNumber, aProposal, ProposalState.PROPOSAL_CONSENSUS);
		else
			newProposalState(aProposalNumber, aProposal,toProposalState(aRejectionKind));
	}
	@Override
	public synchronized void learn(float aProposalNumber, StateType aProposal, ProposalRejectionKind aRejectionKind) {
		ProposalLearnNotificationReceived.newCase(this, getObjectName(), aProposalNumber, aProposal, aRejectionKind);
//		recordProposal(aProposalNumber, aProposal);
		recordReceivedLearnNotification(aProposalNumber, aProposal, aRejectionKind);

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
