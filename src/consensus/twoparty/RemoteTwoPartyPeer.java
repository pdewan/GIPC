package consensus.twoparty;

public interface RemoteTwoPartyPeer<StateType> 
	extends RemoteTwoPartyAcceptor<StateType>, RemoteTwoPartyProposer<StateType>{

}
