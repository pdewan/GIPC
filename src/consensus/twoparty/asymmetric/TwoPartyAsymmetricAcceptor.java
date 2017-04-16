package consensus.twoparty.asymmetric;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;
import consensus.asynchronous.Learner;
import consensus.synchronous.Acceptor;

public interface TwoPartyAsymmetricAcceptor<StateType> extends 
		ConsensusMechanism<StateType>,
		Acceptor<StateType> {
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
