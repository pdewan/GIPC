package inputport.rpc.group;

import inputport.datacomm.group.GroupTrapperFactory;
import inputport.datacomm.group.object.AGroupObjectInputPortLauncherSupport;
import inputport.rpc.duplex.ADuplexRPCInputPortLauncherSupport;

public class AGroupRPCInputPortLauncherSupport extends ADuplexRPCInputPortLauncherSupport {		
	public void setFactories() {
		super.setFactories();
		setGroupTrapperFactories();
		GroupRPCInputPortSelector.setInputPortFactory(new AGroupRPCInputPortFactory());
		AGroupObjectInputPortLauncherSupport.setGroupTrappers();
	}
	public static void setGroupTrapperFactories() {
    	GroupTrapperFactory<Object, Object> factory = new AGroupSerializableCallTrapperFactory();
		GroupSerializableCallTrapperSelector.getTrapperSelector().
			setGroupSendTrapperFactory(factory);
    }

	
}
