package consensus.asynchronous.sequential;

import consensus.AnAbstractConsensusMechanismFactory;
import consensus.ConsensusMechanism;
import sessionport.rpc.group.GIPCSessionRegistry;

public class AnAsynchronousConsensusMechanismFactory<StateType> extends AnAbstractConsensusMechanismFactory<StateType> {

	@Override
	protected ConsensusMechanism instantiateConsensusMehanism(
			GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) {
		return new AnAsynchronousConsensusMechanism(aRegistry, anObjectName, aMyId);
	}

}
