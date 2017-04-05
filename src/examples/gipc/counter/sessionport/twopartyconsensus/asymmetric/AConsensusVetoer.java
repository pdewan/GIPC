package examples.gipc.counter.sessionport.twopartyconsensus.asymmetric;

import consensus.ConsensusVetoer;

public class AConsensusVetoer<StateType> implements ConsensusVetoer<StateType>{
	protected boolean lastResult = false;

	@Override
	public boolean acceptProposal(float aProposalNumber, StateType aState) {
		boolean aResult = !lastResult;
		lastResult = aResult;
		return aResult;
	}

}
