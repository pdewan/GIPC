package port.sessionserver.relay.late;

import port.sessionserver.AServerPortDescription;
import port.sessionserver.ServerPortDescription;
import inputport.rpc.group.GroupRPCInputPortSelector;
import inputport.rpc.group.GroupRPCServerInputPort;
import util.trace.Tracer;

public class ALatecomerRelayerAndSessionServerFactory 
             implements LatecomerRelayerAndSessionServerFactory{

//	@Override
//	public GroupRPCServerInputPort createLatecomerSessionsServerAndRelayer(
//			  String aSessionServerId, String aSessionsServerName, 
//			  String aLogicalServerName) {		
//
//
//		
//		
//		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort( 
//				aSessionServerId, aSessionsServerName);
////		GroupSingleResponseSetter.supportSingleResponse(serverInputPort);
//		LocalLatecomerSessionsServer sessionsServer = new ALatecomerSessionsServer(serverInputPort);
//		serverInputPort.register(sessionsServer);
////		serverInputPort.register(LatecomerSessionsServer.class, sessionsServer);
//		serverInputPort.register(aSessionsServerName, sessionsServer);
//		Tracer.info(this, "Created and registered Latecomer session server object connected to server port " + serverInputPort + " for " + aSessionServerId );
//		GroupRPCServerInputPort relayPort = serverInputPort; // this causes confusion as multiple client input ports may be opened to same server port
//		// those that will use the description should get a separate port, those who do not will simply connect to the same port
//		LatecomerRelayer relayer =  LatecomerRelayerObjectSelector.createRelayer(relayPort, sessionsServer);
//		relayPort.register(relayer);
//
////		relayPort.register(LatecomerRelayer.class, relayer);
//		Tracer.info(this, "Created and registered relayer  object connected to server port " + serverInputPort);
////		relayPort.register(aSessionsServerName, relayer);		
//		ServerPortDescription relayerPortDescription = new AServerPortDescription("localhost", "" + aSessionServerId, aSessionsServerName);		
//		Tracer.info(this, "Registering relayer description " + relayerPortDescription + " with session server");
//		sessionsServer.setRelayerDescripton(relayerPortDescription);
//
////		serverInputPort.connect();	
//		return serverInputPort;
//	}
	@Override
	public GroupRPCServerInputPort createLatecomerSessionsServerAndRelayer(
			  String aSessionServerId, String aSessionsServerName, 
			  String aLogicalServerName) {		


		
		
		GroupRPCServerInputPort serverInputPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort( 
				aSessionServerId, aSessionsServerName); // group so that it can be integrated with the relayer to avoid extra port creation and message cost
//		GroupSingleResponseSetter.supportSingleResponse(serverInputPort);
		LocalLatecomerSessionsServer sessionsServer = new ALatecomerSessionServer(serverInputPort);
		serverInputPort.register(sessionsServer);
//		serverInputPort.register(LatecomerSessionsServer.class, sessionsServer);
		serverInputPort.register(aSessionsServerName, sessionsServer);
		serverInputPort.register(aLogicalServerName, sessionsServer);
		Tracer.info(this, "Created and registered Latecomer session server object connected to server port " + serverInputPort + " for " + aSessionServerId );
		// this causes confusion as multiple client input ports may be opened to same server port
		// however, registering under a different port means an additional port must be chosen and used
		// best to let the client be aware that the session server is also the relayer 
		// by sending null fields in the server port description	
		GroupRPCServerInputPort relayPort = serverInputPort; 
//		ServerPortDescription relayerPortDescription = new AServerPortDescription("localhost", "" + aSessionServerId, aSessionsServerName);		
//		ServerPortDescription relayerPortDescription = new AServerPortDescription(null, null, null);	
		ServerPortDescription relayerPortDescription = null;	

//		GroupRPCServerInputPort relayPort = GroupRPCInputPortSelector.createGroupRPCServerInputPort( 
//				relayerPortDescription.getID(), relayerPortDescription.getName());

		// those that will use the description should get a separate port, those who do not will simply connect to the same port
		LatecomerRelayer relayer =  LatecomerRelayerObjectSelector.createRelayer(relayPort, sessionsServer);
		relayPort.register(relayer);

//		relayPort.register(LatecomerRelayer.class, relayer);
		Tracer.info(this, "Created and registered relayer  object connected to server port " + serverInputPort);
//		relayPort.register(aSessionsServerName, relayer);	
		// regster after creating the relayer
		sessionsServer.setRelayerDescripton(relayerPortDescription);
		Tracer.info(this, "Registered relayer description " + relayerPortDescription + " with session server");


//		serverInputPort.connect();	
		return serverInputPort;
	}

}
