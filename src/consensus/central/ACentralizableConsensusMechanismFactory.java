package consensus.central;

import consensus.AnAbstractConsensusMechanismFactory;
import consensus.ConsensusMechanism;
import sessionport.rpc.group.GIPCSessionRegistry;

public class ACentralizableConsensusMechanismFactory<StateType> extends AnAbstractConsensusMechanismFactory<StateType> {

	@Override
	protected ConsensusMechanism instantiateConsensusMehanism(
			GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) {
		return new ACentralizableConsensusMechanism(aRegistry, anObjectName, aMyId);
	}

}
