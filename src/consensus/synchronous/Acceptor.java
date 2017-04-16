package consensus.synchronous;

import java.rmi.Remote;

import consensus.asynchronous.Learner;

public interface Acceptor<StateType> extends Learner<StateType> {
	void accept(float aProposalNumber, StateType aProposal);


}
