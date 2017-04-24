package consensus.synchronous.sequential;

import java.rmi.Remote;

import consensus.asynchronous.sequential.Learner;

public interface Acceptor<StateType> extends Learner<StateType> {
	void accept(float aProposalNumber, StateType aProposal);


}
