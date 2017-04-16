package consensus.central;

import sessionport.rpc.group.GIPCSessionRegistry;
import consensus.AnAbstractConsensusMechanismFactory;
import consensus.ConsensusMechanism;

public class ACentralizableConsensusMechanismFactory<StateType> extends AnAbstractConsensusMechanismFactory<StateType> {

	@Override
	protected ConsensusMechanism instantiateConsensusMehanism(
			GIPCSessionRegistry aRegistry, String anObjectName, short aMyId) {
		return new ACentralizableConsensusMechanism(aRegistry, anObjectName, aMyId);
	}

}
