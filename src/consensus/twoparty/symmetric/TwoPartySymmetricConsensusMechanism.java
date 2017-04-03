package consensus.twoparty.symmetric;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;

public interface TwoPartySymmetricConsensusMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		RemoteTwoPartyAcceptor<StateType>,
		RemoteTwoPartyLearner<StateType> {
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
