package consensus.paxos;

import consensus.central.ProposeServer;

public interface Preparer<StateType> extends ProposeServer<StateType>{
	void prepare(float aProposalNumber, StateType aProposal);
}
