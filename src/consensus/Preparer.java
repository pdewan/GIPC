package consensus;

import java.rmi.Remote;

public interface Preparer<StateType> extends Remote {
	void prepare(float aProposalNumber, StateType aProposal);


}
