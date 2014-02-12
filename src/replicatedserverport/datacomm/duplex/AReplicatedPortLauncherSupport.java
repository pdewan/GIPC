package replicatedserverport.datacomm.duplex;

import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import replicatedserverport.rpc.group.AReplicatedServerSessionPortFactory;
import replicatedserverport.rpc.group.ReplicatedServerSessionPortSelector;

public class AReplicatedPortLauncherSupport extends AGroupRPCInputPortLauncherSupport {	

public AReplicatedPortLauncherSupport() {
	
}
	
public  void setFactories() {
		super.setFactories();
		ReplicatedServerSessionPortSelector.
	     setReplicatedServerSessionPortFactory(new AReplicatedServerSessionPortFactory());
	}
}
