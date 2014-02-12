package replicatedserverport.datacomm.duplex.earliest;

import replicatedserverport.datacomm.duplex.AReplicatedPortLauncherSupport;
import replicatedserverport.datacomm.simplex.ClientMultiToReplicatedTrapperSelector;
import replicatedserverport.datacomm.simplex.ReplicatedServerObjectTrapperSelector;

public class AnEarliestAcceptingReplicatedPortLauncherSupport extends AReplicatedPortLauncherSupport {	
	
public  void setFactories() {
		super.setFactories();
///		ReplicatedServerObjectTrapperSelector.
		ClientMultiToReplicatedTrapperSelector.
		getTrapperSelector().setReceiveTrapperFactory(new AnEarliestAcceptingMultiToUniReceiveForwarderFactory());


	}
}
