package staticsessionport.datacomm.duplex.buffer;

import port.PortLauncherSupport;
import inputport.datacomm.duplex.buffer.ADuplexBufferInputPortLauncherSupport;
import sessionport.datacomm.duplex.object.ObjectDuplexSessionPortSelector;
import sessionport.datacomm.duplex.object.direct.ADirectObjectDuplexSessionPortFactory;
import sessionport.datacomm.group.object.ObjectGroupSessionPortSelector;
import sessionport.datacomm.group.object.direct.ALayeredObjectGroupSessionPortFactory;

public class AStaticSessionDuplexBufferPortLauncherSupport extends
		ADuplexBufferInputPortLauncherSupport implements PortLauncherSupport {
	public void setFactories() {
		super.setFactories();
		setP2PFactories();
//		ObjectDuplexSessionPortSelector
//				.setDuplexSessionPortFactory(new ADirectObjectDuplexSessionPortFactory());
//		ObjectGroupSessionPortSelector
//				.setGroupSessionPortFactory(new ALayeredObjectGroupSessionPortFactory());

	}
	public static void setP2PFactories() {
		ObjectDuplexSessionPortSelector
		.setDuplexSessionPortFactory(new ADirectObjectDuplexSessionPortFactory());
		ObjectGroupSessionPortSelector
		.setGroupSessionPortFactory(new ALayeredObjectGroupSessionPortFactory());

	}

}
