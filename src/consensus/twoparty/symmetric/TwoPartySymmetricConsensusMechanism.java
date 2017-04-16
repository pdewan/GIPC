package consensus.twoparty.symmetric;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;
import consensus.asynchronous.Learner;
import consensus.synchronous.Acceptor;

public interface TwoPartySymmetricConsensusMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		Acceptor<StateType>,
		Learner<StateType> {
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
