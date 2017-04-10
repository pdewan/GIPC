package consensus.nonatomic.nonvetoable;

import inputport.ConnectionRegistrar;
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
	Learned<StateType> proposer;
	public ALearnerMechanism(ConnectionRegistrar anInputPort, 
			String aName, short aMyId, Learned<StateType> aProposer
			) {
		super(anInputPort, aName, aMyId);
		proposer = aProposer;
	}
	protected Learned<StateType> proposer() {
		return proposer;
	}
	@Override
	public void learn(float aProposalNumber, StateType aProposal, ProposalVetoKind anAgreement) {
		super.learn(aProposalNumber, aProposal, anAgreement);
		if (eventualConsistency() && learnedByTimeout()) {
			return;
		}
		proposer().learned(aProposalNumber, aProposal);		
	}
	


	



	


}
