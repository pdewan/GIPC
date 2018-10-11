package port.sessionserver.relay;

import java.net.InetAddress;

import inputport.rpc.DirectedRPCProxyGenerator;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import port.relay.ARelayerPortFactory;
import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;


public class ASessionServerRelayerPortFactory extends ARelayerPortFactory implements SessionServerRelayerPortFactory {
	public  GroupRPCServerInputPort createRelayerPort(
			String aRelayerServerId, String aRelayerServerName, 
			String sessionsServerHost, String sessionsServerId, String sessionsServerName, String aSessionName) {
		DuplexRPCClientInputPort aSessionServerClientPort = DuplexRPCInputPortSelector.createDuplexRPCClientInputPort(
				sessionsServerHost, sessionsServerId, 
				sessionsServerName, aRelayerServerName);
		return createRelayerPort(aRelayerServerId, aRelayerServerName, aSessionServerClientPort, aSessionName);
//		sessionsServer.setRelayerDescripton(serverPortDescription);	

		
	}
	
	public   GroupRPCServerInputPort createRelayerPort(
			String aRelayerServerId, String aRelayerServerName, 
			DuplexRPCClientInputPort aSessionServerClientPort, String aSessionName) {
		
		GroupRPCServerInputPort retVal = createRelayerPort (aRelayerServerId, aRelayerServerName);
		String myHostName = "localhost";
		try {
				myHostName = InetAddress.getLocalHost().getHostName();
		} catch (Exception e) {
			e.printStackTrace();
		}
		ServerPortDescription serverPortDescription = new AServerPortDescription(myHostName, aRelayerServerId, aRelayerServerName);
		RelayerSupportingSessionServer sessionsServer =  (RelayerSupportingSessionServer) DirectedRPCProxyGenerator.generateRPCProxy(aSessionServerClientPort, null, ARelayerSupportingSessionServer.class, aSessionServerClientPort.getLogicalRemoteEndPoint());
		aSessionServerClientPort.addConnectionListener(new ASessionServerToRelayerConnectionListener(aSessionServerClientPort, sessionsServer, serverPortDescription, aSessionName));
		aSessionServerClientPort.connect();
		return retVal;

		
	}
	
	
//	
//
//	public   GroupRPCServerInputPort createRelayerPort (String relayerId, String relayerName) {
//		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort(relayerId, relayerName);
//		Relayer relayer = RelayerObjectSelector.createRelayer(serverInputPort);
//		serverInputPort.register(Relayer.class, relayer);
//		serverInputPort.register(relayerName, relayer);
//		return serverInputPort;
//				
//	}

	

}
