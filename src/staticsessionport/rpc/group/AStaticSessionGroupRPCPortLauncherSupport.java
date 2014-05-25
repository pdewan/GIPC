package staticsessionport.rpc.group;

import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import port.PortLauncherSupport;
import port.sessionserver.relay.RelayerClientAndServerSupport;

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
