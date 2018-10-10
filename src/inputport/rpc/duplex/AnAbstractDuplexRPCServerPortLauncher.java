package inputport.rpc.duplex;

import java.util.HashMap;
import java.util.Map;

import inputport.rpc.RPCRegistry;
import port.PortKind;

public class AnAbstractDuplexRPCServerPortLauncher extends ADuplexRPCPortLauncher {


//public class AnAbstractDuplexRPCServerPortLauncher extends AnAbstractPortLauncher implements GIPCRegistry  {
	protected Map<String, Object> registry = new HashMap();
	public AnAbstractDuplexRPCServerPortLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}	
//	@Override
//	protected PortAccessKind getPortAccessKind() {
//		return PortAccessKind.DUPLEX;
//	}	
	public AnAbstractDuplexRPCServerPortLauncher() {
	}	
	@Override
	public DuplexRPCServerInputPort getRPCServerPort() {
		return (DuplexRPCServerInputPort) getInputPort();
	}
	@Override
	public PortKind getPortKind() {
		return PortKind.SERVER_INPUT_PORT;
	}
	public Object lookup(Class anInterface, String aName) {
		return ((RPCRegistry) getInputPort()).getServerObject(aName);
	}
//	@Override
//	protected PortMessageKind getPortMessageKind() {
//		return PortMessageKind.RPC;
//	}
//	@Override
//	protected void registerRemoteObjects() {		
//		for (String aKey:registry.keySet()) {
//			register(aKey, registry.get(aKey));
//		}
//	}
//	@Override
//	public void rebindAndExport(String aName, Object anObject) {
//		rebind(aName, anObject);
//		launch();
//	}
//	
//	public void rebind(String aName, Object anObject) {
//		registry.put(aName, anObject);		
//	}
//
//	public void exportObjects() {
//		launch();
//	}	
}
