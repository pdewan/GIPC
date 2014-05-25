package port.relay;

import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.sessionserver.relay.RelayerObjectSelector;


public class ARelayerPortFactory implements RelayerPortFactory {
//	public  GroupRPCServerInputPort createRelayerPort(
//			String aRelayerServerId, String aRelayerServerName, 
//			String sessionsServerHost, String sessionsServerId, String sessionsServerName, String aSessionName) {
//		DuplexRPCClientInputPort aSessionServerClientPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
//				sessionsServerHost, sessionsServerId, 
//				sessionsServerName, aRelayerServerName);
//		return createRelayerPort(aRelayerServerId, aRelayerServerName, aSessionServerClientPort, aSessionName);
////		sessionsServer.setRelayerDescripton(serverPortDescription);	
//
//		
//	}
//	
//	public   GroupRPCServerInputPort createRelayerPort(
//			String aRelayerServerId, String aRelayerServerName, 
//			DuplexRPCClientInputPort aSessionServerClientPort, String aSessionName) {
//		
//		GroupRPCServerInputPort retVal = createRelayerPort (aRelayerServerId, aRelayerServerName);
//		ServerPortDescription serverPortDescription = new AServerPortDescription("localhost", aRelayerServerId, aRelayerServerName);
//		RelayerSupportingSessionServer sessionsServer =  (RelayerSupportingSessionServer) DirectedRPCProxyGenerator.generateRPCProxy(aSessionServerClientPort, null, RelayerSupportingSessionServer.class, aSessionServerClientPort.getLogicalRemoteEndPoint());
//		aSessionServerClientPort.addConnectionListener(new ASessionServerToRelayerConnectionListener(sessionsServer, serverPortDescription, aSessionName));
//		aSessionServerClientPort.connect();
//		return retVal;
//
//		
//	}
	
	
	

	public   GroupRPCServerInputPort createRelayerPort (String relayerId, String relayerName) {
		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort(relayerId, relayerName);
		Relayer relayer = RelayerObjectSelector.createRelayer(serverInputPort);
		serverInputPort.register(ARelayer.class, relayer);
		serverInputPort.register(relayerName, relayer);
		return serverInputPort;
				
	}

	

}
