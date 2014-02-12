package port.sessionserver.relay.mvc.example;

import inputport.ConnectionListener;
import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPort;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;
import port.relay.ARelayer;
import port.relay.Relayer;
import port.relay.mvc.example.AGenericRelayingCollaborativeFrostyModel;
import port.relay.mvc.example.GenericRelayingCollaborativeFrostyModel;
import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.local.duplex.DuplexUpperCaser;

public class AnMVCServerConnectionListenerAndLauncher extends ADuplexRPCClientMVCLauncher implements ConnectionListenerWithPort{
	protected DuplexRPCClientInputPort mvcClientPort;
	protected DuplexRPCClientInputPort relayerPort;
	Relayer relayerProxy;
//	public AUICreatingConnectListener(DuplexRPCClientInputPort aClientInputPort) {
//		mvcClientPort = aClientInputPort;
//	}
	
	public AnMVCServerConnectionListenerAndLauncher (DuplexRPCClientInputPort aRelayerPort) {
		relayerPort = aRelayerPort;	
		relayerProxy = (Relayer) relayerPort.getRPCProxyGenerator().generateRPCProxy(ARelayer.class);
	}
	
	protected DuplexFrostyModel getFrostyModel() {
		GenericRelayingCollaborativeFrostyModel genericModel =  new  AGenericRelayingCollaborativeFrostyModel((DuplexUpperCaser)upperCaseProxy, relayerProxy, counter, relayerPort.getLocalName());	
		((DuplexRPCInputPort) relayerPort).addReceiveListener(genericModel);
		return genericModel;
	}

	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		System.out.println(mvcClientPort.getLocalName() + "<-->" + aRemoteEndName + " (Opened)");	
		this.connectedToAllPorts();
//		
//		createUI(mvcClientPort);
	}
	
	@Override
	public void disconnected(String aRemoteEnd, boolean anExplicitClose, String aSystemMessage, ConnectionType aConnectionType) {
		System.out.println(mvcClientPort.getLocalName() + "<-->" + aRemoteEnd + " (Closed)");
		if (anExplicitClose) {
			System.out.println("Explanation: Explicit disconnect");
		} else {
			System.out.println("Explanation: " + aSystemMessage);
		}
	}
	
	@Override
	public void notConnected(String aRemoteEnd, String aMessage, ConnectionType aConnectionType) {
		System.out.println(mvcClientPort.getLocalName() + "<-->" + aRemoteEnd + " (Could not connect because: " + aMessage + " )");	
		
	}

	@Override
	public void initInputPort(InputPort anInputPort) {
		mvcClientPort = (DuplexRPCClientInputPort) anInputPort;
		mainPort = mvcClientPort;
		createProxies();
		registerRemoteObjects();
		
	}

//	@Override
//	public InputPort getInputPort() {
//		return mvcClientPort;
//	}

}
