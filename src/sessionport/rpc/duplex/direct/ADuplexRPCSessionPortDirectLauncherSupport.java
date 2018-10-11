package sessionport.rpc.duplex.direct;

import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.duplex.object.direct.ADirectObjectDuplexSessionPortFactory;

public class ADuplexRPCSessionPortDirectLauncherSupport extends ADuplexRPCInputPortLauncherSupport{
	public  void setFactories() {
		super.setFactories();
		ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
				new ADirectObjectDuplexSessionPortFactory());
	}
}
