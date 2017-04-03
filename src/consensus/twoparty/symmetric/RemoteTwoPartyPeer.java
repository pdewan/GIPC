package consensus.twoparty.symmetric;

import consensus.Learner;

public interface RemoteTwoPartyPeer<StateType> 
	extends Acceptor<StateType>, Learner<StateType>{

}
