package consensus;

import java.rmi.Remote;

public interface Acceptor<StateType> extends Learner<StateType> {
	void accept(float aProposalNumber, StateType aProposal);


}
