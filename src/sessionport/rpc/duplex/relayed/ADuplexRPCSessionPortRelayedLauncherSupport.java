package sessionport.rpc.duplex.relayed;

import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.duplex.object.relayed.ARelayingObjectDuplexSessionPortFactory;

public class ADuplexRPCSessionPortRelayedLauncherSupport extends ADuplexRPCInputPortLauncherSupport{
	public  void setFactories() {
		super.setFactories();
		ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
				new ARelayingObjectDuplexSessionPortFactory());
	}
}
