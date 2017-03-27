package inputport.rpc.duplex;

import java.util.HashMap;
import java.util.Map;

import examples.mvc.local.duplex.ACounter;
import inputport.rpc.GIPCRegistry;
import inputport.rpc.RPCInputPort;
import port.AnAbstractPortLauncher;
import port.ParticipantChoice;
import port.PortAccessKind;
import port.PortKind;
import port.PortLauncherSupport;
import port.PortMessageKind;
import port.SessionChoice;
import port.sessionserver.AServerPortDescription;



public class ADuplexRPCPortLauncher extends AnAbstractPortLauncher implements GIPCRegistry  {
	protected Map<String, Object> registry = new HashMap();
	public ADuplexRPCPortLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	public ADuplexRPCPortLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}
	public ADuplexRPCPortLauncher(String aSessionServerHost, 
			String aServerId, 
			String aServerName, 
			String aMyId, 
			String aMyName, 
			String aSessionName,
			SessionChoice aSessionChoice, 
			boolean aShouldDelay,
			PortLauncherSupport aDelaysSupport,
			boolean aDoJitter,
			boolean aDoCausal, 			
			ParticipantChoice aChoice) {
		super(aSessionServerHost, aServerId, aServerName, aMyId, aMyName, aSessionName, aSessionChoice, aShouldDelay, aDelaysSupport, aDoJitter, aDoCausal, aChoice);
				
		
	}
	@Override
	protected boolean startAsyncThread() {
		return false;
	}
	@Override
	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.DUPLEX;
	}	
	public ADuplexRPCPortLauncher() {
	}	
	
	@Override
	public PortMessageKind getPortMessageKind() {
		return PortMessageKind.RPC;
	}
	@Override
	protected void registerRemoteObjects() {		
		for (String aKey:registry.keySet()) {
			register(aKey, registry.get(aKey));
		}
	}
	
//	@Override
//	public void rebindAndListen(String aName, Object anObject) {
//		rebind(aName, anObject);
//		launch();
//	}
	
	public void rebind(String aName, Object anObject) {
//		registry.put(aName, anObject);
		register(aName, anObject);
	}
	
	public DuplexRPCClientInputPort getRPCClientPort() {
		return null;
	}
	public DuplexRPCServerInputPort getRPCServerPort() {
		return null;
	}
	public RPCInputPort getRPCInputPort() {
		return (RPCInputPort) getInputPort();
	}

//	public void listen() {
//		launch();
//	}	
}
