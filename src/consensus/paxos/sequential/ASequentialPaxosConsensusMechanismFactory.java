package consensus.paxos.sequential;

import consensus.AnAbstractConsensusMechanismFactory;
import consensus.ConsensusMechanism;
import sessionport.rpc.group.GIPCSessionRegistry;

public class ASequentialPaxosConsensusMechanismFactory<StateType> extends AnAbstractConsensusMechanismFactory<StateType> {

	@Override
	protected ConsensusMechanism instantiateConsensusMehanism(
			GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) {
		return new ASequentialPaxosConsensusMechanism<>(aRegistry, anObjectName, aMyId);
	}

}
