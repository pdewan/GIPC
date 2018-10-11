package consensus.paxos;

import consensus.AnAbstractConsensusMechanismFactory;
import consensus.ConsensusMechanism;
import sessionport.rpc.group.GIPCSessionRegistry;

public class APaxosConsensusMechanismFactory<StateType> extends AnAbstractConsensusMechanismFactory<StateType> {

	@Override
	protected ConsensusMechanism instantiateConsensusMehanism(
			GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) {
		return new APaxosConsensusMechanism(aRegistry, anObjectName, aMyId);
	}

}
