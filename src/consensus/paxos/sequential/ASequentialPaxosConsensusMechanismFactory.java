package consensus.paxos.sequential;

import sessionport.rpc.group.GIPCSessionRegistry;
import consensus.AnAbstractConsensusMechanismFactory;
import consensus.ConsensusMechanism;

public class ASequentialPaxosConsensusMechanismFactory<StateType> extends AnAbstractConsensusMechanismFactory<StateType> {

	@Override
	protected ConsensusMechanism instantiateConsensusMehanism(
			GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) {
		return new ASequentialPaxosConsensusMechanism<>(aRegistry, anObjectName, aMyId);
	}

}
