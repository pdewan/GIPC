package consensus.nonatomic.nonvetoable;

import inputport.ConnectionRegistrar;
import inputport.InputPort;
import port.trace.consensus.ProposalConsensusOccurred;
import port.trace.consensus.ProposalLearnNotificationReceived;
import port.trace.consensus.ProposalLearnNotificationSent;
import port.trace.consensus.ProposalLearnedNotificationSent;
import consensus.Accepted;
import consensus.AnAbstractConsensusMechanism;
import consensus.Learned;
import consensus.Learner;
import consensus.ProposalState;
import consensus.ProposalVetoKind;

public class ALearnerMechanism<StateType> extends
		AnAbstractConsensusMechanism<StateType>  {
	public ALearnerMechanism(InputPort anInputPort, 
			String aName, short aMyId
			) {
		super(anInputPort, aName, aMyId);
	}
//	protected Learned<StateType> proposer() {
//		return proposer;
//	}
	protected void setLearnedState(float aProposalNumber, StateType aProposal, ProposalVetoKind anAgreement) {
		if (!eventualConsistency() && learnedByTimeout()) {
			waitForReceipt(aProposalNumber, aProposal);			
		}
		super.setLearnedState(aProposalNumber, aProposal, anAgreement);
	}
//	@Override
//	public void learn(float aProposalNumber, StateType aProposal, ProposalVetoKind anAgreement) {
//		super.learn(aProposalNumber, aProposal, anAgreement);
//		if (eventualConsistency() && learnedByTimeout()) {
//			return;
//		}
//		proposer().learned(aProposalNumber, aProposal);		
//	}
	


	



	


}
