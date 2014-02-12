package port.relay.mvc.example;


import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.mvc.singleuser.example.ADuplexRPCClientMVCLauncher;
import inputport.rpc.simplex.mvc.example.SimplexRPCServerMVCLauncher;
import port.relay.ARelayer;
import port.relay.Relayer;
import port.relay.RelayerLauncher;
import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.local.duplex.DuplexUpperCaser;

public class ATwoServerClientLauncher extends ADuplexRPCClientMVCLauncher  {
	String relayerHost, relayerId, relayerName;
	Relayer relayerProxy;
	GenericRelayingCollaborativeFrostyModel genericModel;
	public ATwoServerClientLauncher(String aClientName) {
		super(aClientName);
	}
	public ATwoServerClientLauncher() {
	}
	public ATwoServerClientLauncher(String aClientName, String aServerHost, String aServerId, String aServerName, String aRelayerHost, String aRelayerId, String aRelayerName) {
		super(aClientName, aServerHost, aServerId, aServerName);
		relayerHost =aRelayerHost;
		relayerId = aRelayerId;
		relayerName = aRelayerName;
	}
	protected  void createProxies() {
		super.createProxies();
		relayerProxy = (Relayer) ((DuplexRPCClientInputPort) auxilliaryPort).
				getRPCProxyGenerator().generateRPCProxy(ARelayer.class);
//		relayerProxy = (Relayer) DirectedRPCProxyGenerator.generateRPCProxy((DuplexRPCClientInputPort) auxilliaryPort, 
//				ARelayer.class);
	}
	protected  InputPort getAuxilliaryPort() {
		return DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
				relayerHost, relayerId, relayerName, 	clientName);	
	}	
	protected  ConnectionListener getAuxilliaryConnectionListener (InputPort anInputPort) {
		return getConnectionListener(anInputPort);
	}
	@Override
	protected  ReceiveListener getAuxilliaryReceiveListener (InputPort anInputPort) {
		return (ReceiveListener) getFrostyModel();
	}
	protected DuplexFrostyModel getFrostyModel() {
		if (genericModel == null)
		  genericModel =  new  AGenericRelayingCollaborativeFrostyModel((DuplexUpperCaser)upperCaseProxy, relayerProxy, counter, clientName);	
//		((DuplexRPCInputPort) auxilliaryPort).addReceiveListener(genericModel);
		return genericModel;
	}	
	public static void main (String[] args) {		
		(new ATwoServerClientLauncher(
				CLIENT_NAME, 
				"localhost", SimplexRPCServerMVCLauncher.MVC_SERVER_ID, SimplexRPCServerMVCLauncher.MVC_SERVER_NAME,
				"localhost", RelayerLauncher.RELAYER_ID, RelayerLauncher.RELAYER_NAME
				)).launch();
	}
}
