package consensus.twoparty.symmetric;

public interface RemoteTwoPartyPeer<StateType> 
	extends RemoteTwoPartyAcceptor<StateType>, RemoteTwoPartyLearner<StateType>{

}
