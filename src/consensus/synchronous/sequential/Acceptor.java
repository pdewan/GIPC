package consensus.synchronous.sequential;

import consensus.asynchronous.sequential.Learner;

public interface Acceptor<StateType> extends Learner<StateType> {
	void accept(float aProposalNumber, StateType aProposal);


}
