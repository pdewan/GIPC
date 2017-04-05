package consensus.twoparty.asymmetric;

import inputport.ConnectionListener;
import consensus.Acceptor;
import consensus.ConsensusMechanism;
import consensus.Learner;

public interface TwoPartyAsymmetricAcceptor<StateType> extends 
		ConsensusMechanism<StateType>,
		Acceptor<StateType> {
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
