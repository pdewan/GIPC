package consensus.paxos;

import consensus.ProposalRejectionKind;
import consensus.central.ProposeServer;

public interface Prepared<StateType> extends Preparer<StateType>{
	void prepared(float aProposalNumber, StateType aProposal, float aPreparedProposalNumber, ProposalRejectionKind aRejectionKind);
}
