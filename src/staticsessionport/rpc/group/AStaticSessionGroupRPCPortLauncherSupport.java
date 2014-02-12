package staticsessionport.rpc.group;

import port.PortLauncherSupport;
import port.sessionserver.relay.RelayerClientAndServerSupport;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;

public class AStaticSessionGroupRPCPortLauncherSupport extends AGroupRPCInputPortLauncherSupport implements PortLauncherSupport {
	public  void setFactories() {
		super.setFactories();
//		setDuplexFactories();
		RelayerClientAndServerSupport.setRelayedCommunicaton(false);
		
	}
//	protected void setDuplexFactories() {
//		ADuplexRPCInputPortLauncherSupport.setDuplexTrapperFactories();
//		ADuplexRPCInputPortLauncherSupport.setDuplexCallInvokerCompleterFactories();
//		ADuplexRPCInputPortLauncherSupport.setSimplexMarshallerfactory();
//	}
	
}
