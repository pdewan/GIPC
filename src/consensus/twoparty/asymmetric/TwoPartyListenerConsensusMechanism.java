package consensus.twoparty.asymmetric;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;
import consensus.twoparty.symmetric.RemoteTwoPartyLearner;

public interface TwoPartyListenerConsensusMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		RemoteAsymmetricTwoPartyLearner<StateType> {
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
