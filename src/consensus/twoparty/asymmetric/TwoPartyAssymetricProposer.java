package consensus.twoparty.asymmetric;

import consensus.ConsensusMechanism;
import consensus.synchronous.Accepted;

public interface TwoPartyAssymetricProposer<StateType> extends 
		ConsensusMechanism<StateType>,
		Accepted<StateType>{
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
