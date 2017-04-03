package consensus.twoparty.asymmetric;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;
import consensus.twoparty.symmetric.RemoteTwoPartyAcceptor;

public interface TwoPartyAcceptorConsensusMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		RemoteTwoPartyAcceptor<StateType>{
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
