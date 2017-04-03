package consensus.twoparty.symmetric;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;
import consensus.Learner;

public interface TwoPartySymmetricConsensusMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		Acceptor<StateType>,
		Learner<StateType> {
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
