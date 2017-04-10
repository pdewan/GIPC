package consensus.nonatomic.nonvetoable;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;
import consensus.Learned;
import consensus.Learner;

public interface LearnedMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		Learned<StateType> {
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
