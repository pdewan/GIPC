package consensus;

import java.util.Set;

import inputport.ConnectionListener;


public interface BasicConsensusMechanism<StateType>   {
	/**
	 * propose a new value, and get back an ID for the proposal
	 */
	float propose(StateType aProposal);		
	/**
	 * Register a consensus listner, calld each time global consensus is reached
	 */
	

	void addConsensusListener(ConsensusListener<StateType> aConsensusListener);
	/**
	 * remove consensus listener
	 */
	void removeConsensusListener(ConsensusListener<StateType> aConsensusListener);
	
	


}
