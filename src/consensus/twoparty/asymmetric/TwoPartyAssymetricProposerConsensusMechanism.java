package consensus.twoparty.asymmetric;

import consensus.Accepted;
import consensus.ConsensusMechanism;

public interface TwoPartyAssymetricProposerConsensusMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		Accepted<StateType>{
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
