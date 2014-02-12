package port.sessionserver.relay.mvc.example;

import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPort;
import port.relay.ARelayer;
import port.relay.Relayer;
import port.relay.mvc.example.GenericRelayingCollaborativeFrostyModel;
import port.sessionserver.ServerPortDescription;

public class ARelayerConnectionListener extends AServerConnectingConnectionListener {
//	Relayer relayerProxy;
	protected GenericRelayingCollaborativeFrostyModel model;
	public ARelayerConnectionListener(
			ServerPortDescription aServerPortDescription, String aClientName,
			ConnectionListenerWithPort aConnectionListener, GenericRelayingCollaborativeFrostyModel aModel) {
		super(aServerPortDescription, aClientName, aConnectionListener);
		model = aModel;
	}
	@Override
	public void connected(String aRemoteEnd, ConnectionType aConnectionType) {	
		traceConnected(aRemoteEnd, aConnectionType);
		ConnectionListenerWithPort mvcConnectionListener = createMVCConnectionListener();
		nextConnectionListener = mvcConnectionListener;
		super.connected(aRemoteEnd, aConnectionType); 
	}
	protected ConnectionListenerWithPort createMVCConnectionListener() {
		return new AnMVCServerConnectionListener(model);
	}
	protected void addModelAsReceiveListener() {
		((DuplexRPCClientInputPort) inputPort).addReceiveListener(model);		
	}
	@Override
	public void initInputPort(InputPort anInputPort) {
		super.initInputPort(anInputPort);	
		Relayer relayerProxy = (Relayer) ((DuplexRPCInputPort) inputPort).getRPCProxyGenerator().generateRPCProxy(ARelayer.class);
		addModelAsReceiveListener();
//		((DuplexRPCClientInputPort) inputPort).addReceiveListener(model);
		model.setRelayer(relayerProxy);
	}
	

}
