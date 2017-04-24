package consensus.central;

import consensus.synchronous.sequential.Accepted;

public interface ProposeServer<StateType>  extends Accepted<StateType>{
	 void remotePropose (float aProposalNumber, StateType aProposal);


}
