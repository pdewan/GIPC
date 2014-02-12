package inputport.rpc.group.adder.example;


import port.PortLauncherSupport;
import inputport.InputPort;
import inputport.rpc.RPCRegistry;
import inputport.rpc.duplex.example.ADuplexRPCClientInputPortLauncher;
import inputport.rpc.duplex.example.AnotherCounter;
import inputport.rpc.group.AGroupRPCInputPortLauncherSupport;
import inputport.rpc.group.GroupRPCInputPortSelector;
public class AGroupRPCClientInputPortLauncher extends ADuplexRPCClientInputPortLauncher  {
	public AGroupRPCClientInputPortLauncher(String aClientName) {
		super(aClientName);
	}
	protected PortLauncherSupport getPortLauncherSupport() {
		return new AGroupRPCInputPortLauncherSupport();
	}
	@Override
	protected InputPort getPort() {
		return GroupRPCInputPortSelector.createDuplexRPCClientInputPort(
				SERVER_HOST, SERVER_ID, SERVER_NAME, 	clientName);
	}
	@Override
	protected AnotherCounter createAndRegisterCounter(RPCRegistry aClientInputPort) {
		AnotherCounter counter = new ACounterWithObjectValue();
//		aClientInputPort.register(CounterWithObjectValue.class, counter);
//		aClientInputPort.register(Counter.class, counter);
		aClientInputPort.register(counter);
//		aClientInputPort.register(Counter.class, counter);
		return counter;
	}	
	


}
