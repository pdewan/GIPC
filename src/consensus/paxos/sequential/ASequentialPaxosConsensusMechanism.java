package consensus.paxos.sequential;

import consensus.paxos.APaxosConsensusMechanism;
import sessionport.rpc.group.GIPCSessionRegistry;

public class ASequentialPaxosConsensusMechanism<StateType> extends
		APaxosConsensusMechanism<StateType> {

	

	public ASequentialPaxosConsensusMechanism(GIPCSessionRegistry aRegistry,
			String aName, short aMyId) {
		super(aRegistry, aName, aMyId);
	}
	@Override
	protected StateType preparedProposal(float aPreparedProposalNumber) {
		if (isSequentialAccess()) {
			return proposal(aPreparedProposalNumber);
		}
		return super.preparedProposal(aPreparedProposalNumber);
	}
	

}
