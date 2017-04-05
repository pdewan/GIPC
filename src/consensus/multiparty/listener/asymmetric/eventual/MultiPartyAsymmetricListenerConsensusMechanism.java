package consensus.multiparty.listener.asymmetric.eventual;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;
import consensus.Learner;

public interface MultiPartyAsymmetricListenerConsensusMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		Learner<StateType> {
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
