package sessionport.datacomm.group.object.direct;

import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.duplex.object.direct.ADirectObjectDuplexSessionPortFactory;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;

public class AnObjectGroupSessionPortDirectLauncherSupport extends AGroupRPCInputPortLauncherSupport {	// needs rpc + group comm 
	public  void setTracing() {
		super.setTracing();
	}

	public  void setFactories() {
		super.setFactories();		
		setDirectCommunicaton();
//		GlobalDelayState.setDelayClientBufferSends(true);

	}

	
	
	public static void setDirectCommunicaton() {		
			ObjectDuplexSessionPortSelector.setDuplexSessionPortFactory(
					new ADirectObjectDuplexSessionPortFactory());
			ObjectGroupSessionPortSelector.setGroupSessionPortFactory(
					new ALayeredObjectGroupSessionPortFactory());			
			
	}

	
}
