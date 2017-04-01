package consensus.twoparty;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;

public interface TwoPartyConsensusMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		RemoteTwoPartyAcceptor<StateType>,
		RemoteTwoPartyProposer<StateType>,
		ConnectionListener{
		
		
}
