package replicatedserverport.datacomm.duplex;

import replicatedserverport.datacomm.simplex.ClientMultiToReplicatedTrapperSelector;
import replicatedserverport.datacomm.simplex.MultiToReplicatedTrapperSelector;
import replicatedserverport.datacomm.simplex.ReplicatedServerObjectTrapperSelector;

public class ADuplexMultiToReplicatedPortLauncherSupport extends AReplicatedPortLauncherSupport {	
	
public  void setFactories() {
		super.setFactories();
//		ReplicatedServerObjectTrapperSelector.

		ClientMultiToReplicatedTrapperSelector.
		getTrapperSelector().setReceiveTrapperFactory(new ADuplexMultiToReplicatedTrapperFactory());


	}
}
