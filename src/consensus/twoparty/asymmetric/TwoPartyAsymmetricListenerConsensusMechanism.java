package consensus.twoparty.asymmetric;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;
import consensus.Learner;

public interface TwoPartyAsymmetricListenerConsensusMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		Learner<StateType> {
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
