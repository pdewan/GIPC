package consensus.synchronous.sequential;

import consensus.AnAbstractConsensusMechanismFactory;
import consensus.ConsensusMechanism;
import sessionport.rpc.group.GIPCSessionRegistry;

public class ASynchronousConsensusMechanismFactory<StateType> extends AnAbstractConsensusMechanismFactory<StateType> {

	@Override
	protected ConsensusMechanism instantiateConsensusMehanism(
			GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) {
		return new ASynchronousConsensusMechanism(aRegistry, anObjectName, aMyId);
	}

}
