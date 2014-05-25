package replicatedserverport.datacomm.duplex;

import replicatedserverport.datacomm.simplex.ClientMultiToReplicatedTrapperSelector;

public class ADuplexMultiToReplicatedPortLauncherSupport extends AReplicatedPortLauncherSupport {	
	
public  void setFactories() {
		super.setFactories();
//		ReplicatedServerObjectTrapperSelector.

		ClientMultiToReplicatedTrapperSelector.
		getTrapperSelector().setReceiveTrapperFactory(new ADuplexMultiToReplicatedTrapperFactory());


	}
}
