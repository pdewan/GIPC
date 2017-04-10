package consensus;

import java.rmi.Remote;

public interface Learned<StateType> extends Remote{
	void learned(float aProposalNumber, StateType aProposal);
	final String LEARNED_NOTIFICATION = "LEARNED_NOTIFICATION";
	final String LEARNED_SUCCESS = "LEARNED_SUCCESS";



}
