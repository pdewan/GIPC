package sessionport.rpc.duplex.relayed;

import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.duplex.object.relayed.ARelayingObjectDuplexSessionPortFactory;
import inputport.rpc.duplex.ADuplexRPCInputPortFactory;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import inputport.rpc.duplex.AFunctionReturnValueDeterminer;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.FunctionReturnValueDeterminerSelector;

public class ADuplexRPCSessionPortRelayedLauncherSupport extends ADuplexRPCInputPortLauncherSupport{
	public  void setFactories() {
		super.setFactories();
		ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
				new ARelayingObjectDuplexSessionPortFactory());
	}
}
