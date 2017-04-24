package consensus.synchronous.sequential;

import sessionport.rpc.group.GIPCSessionRegistry;
import sessionport.rpc.group.GroupRPCSessionPort;
import consensus.AnAbstractConsensusMechanismFactory;
import consensus.ConsensusMechanism;

public class ASynchronousConsensusMechanismFactory<StateType> extends AnAbstractConsensusMechanismFactory<StateType> {

	@Override
	protected ConsensusMechanism instantiateConsensusMehanism(
			GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) {
		return new ASynchronousConsensusMechanism(aRegistry, anObjectName, aMyId);
	}

}
