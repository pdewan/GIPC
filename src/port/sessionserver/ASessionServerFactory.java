package port.sessionserver;

import java.util.ArrayList;
import java.util.List;

import inputport.rpc.duplex.DuplexRPCInputPortSelector;
import inputport.rpc.duplex.DuplexRPCServerInputPort;



public class ASessionServerFactory implements SessionServerFactory {
	
	public DuplexRPCServerInputPort createSessionServerPort (String aServerId, String aServerName) {
		return createSessionServerPort(aServerId, aServerName, aServerName);	
	}
	public DuplexRPCServerInputPort createSessionServerPort (String aServerId, String aServerName, String aLogicalServerName) {
//		LocalSessionsServer aSessionsServer = new ASessionsServer();
		LocalSessionsServer aSessionsServer = createLocalSessionServerObject();
//		DuplexRPCServerInputPort retVal = createSessionServer(aServerId, aServerName, aSessionsServer, SessionsServer.class, aLogicalServerName);
		DuplexRPCServerInputPort retVal = createSessionServer(aServerId, aServerName, aSessionsServer, getSessionServerInterfaces(), aLogicalServerName);
//		aSessionsServer.setDuplexRPCServerInputPort(retVal);

		return retVal;
	}
	
	protected List<Class> getSessionServerInterfaces() {
		List<Class> retVal = new ArrayList();
		retVal.add(ASessionServer.class);
		return retVal;
	}
	
	protected LocalSessionsServer createLocalSessionServerObject() {
		return new ASessionServer();
	}
	
	

	public static DuplexRPCServerInputPort createSessionServer(String aServerId, 
			String aServerName, 
			LocalSessionsServer aSessionsServer,
			List<Class> aSessionsServerInterfaces, String aRegisteredServerName) {		
		DuplexRPCServerInputPort serverInputPort = DuplexRPCInputPortSelector.createDuplexRPCServerInputPort(aServerId, aServerName);
//		SessionsServer sessionServer = new ASessionsServer(serverInputPort);
		aSessionsServer.setDuplexRPCServerInputPort(serverInputPort);
//		for (Class aSessionsServerInterface:aSessionsServerInterfaces)
//		   serverInputPort.register(aSessionsServerInterface, aSessionsServer);
		serverInputPort.register(aSessionsServer);
//		serverInputPort.register(LatecomerSessionsServer.class, sessionsServer);
//		serverInputPort.register(aSessionsServerName, sessionsServer);
		serverInputPort.register(aRegisteredServerName, aSessionsServer);
//		serverInputPort.connect();	
		return serverInputPort;
	}
	public static DuplexRPCServerInputPort createSessionServer(String aServerId, 
			String aServerName, 
			LocalSessionsServer aSessionsServer,
			List<Class> aSessionsServerInterfaces) {		
		return createSessionServer(aServerId, aServerName, aSessionsServer, aSessionsServerInterfaces, aServerName);
	}
	
}
