package consensus.paxos;

import sessionport.rpc.group.GIPCSessionRegistry;
import consensus.AnAbstractConsensusMechanismFactory;
import consensus.ConsensusMechanism;

public class APaxosConsensusMechanismFactory<StateType> extends AnAbstractConsensusMechanismFactory<StateType> {

	@Override
	protected ConsensusMechanism instantiateConsensusMehanism(
			GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) {
		return new APaxosConsensusMechanism(aRegistry, anObjectName, aMyId);
	}

}
