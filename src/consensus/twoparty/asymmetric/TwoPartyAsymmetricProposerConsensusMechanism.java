package consensus.twoparty.asymmetric;

import inputport.ConnectionListener;
import consensus.ConsensusMechanism;
import consensus.Learned;
import consensus.twoparty.symmetric.Acceptor;

public interface TwoPartyAsymmetricProposerConsensusMechanism<StateType> extends 
		ConsensusMechanism<StateType>,
		Learned<StateType>{
		
//	 void setPeerProxy(RemoteTwoPartyPeer<StateType> aPeerProxy);
		
		
}
