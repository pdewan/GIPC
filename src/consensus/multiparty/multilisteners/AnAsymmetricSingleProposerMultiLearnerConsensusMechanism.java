package consensus.multiparty.multilisteners;

import inputport.ConnectionRegistrar;
import consensus.Learned;
import consensus.MultiPartyLearner;
import consensus.twoparty.asymmetric.AnAsymmetricTwoPartyLearnerConsensusMechanism;

public class AnAsymmetricSingleProposerMultiLearnerConsensusMechanism<StateType> 
	extends AnAsymmetricTwoPartyLearnerConsensusMechanism<StateType> 
	implements MultiPartyLearner<StateType>{

	public AnAsymmetricSingleProposerMultiLearnerConsensusMechanism(
			ConnectionRegistrar anInputPort, String aName, short aMyId,
			Learned<StateType> aProposer) {
		super(anInputPort, aName, aMyId, aProposer);
	}

	@Override
	public void prepare(float aProposalNumber, StateType aProposal) {
		// TODO Auto-generated method stub
		
	}

}
