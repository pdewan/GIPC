package port.sessionserver;

import inputport.rpc.duplex.DuplexRPCServerInputPort;

public class SessionServerSelector {
	static SessionServerFactory factory;
	public static void setSessionServerFactory(SessionServerFactory aFactory) {
		factory = aFactory;
	}
	public static SessionServerFactory getSessionServerFactory() {
		return factory;
	}
	public static DuplexRPCServerInputPort createSessionServer (String aServerId, String aServerName) {
		return factory.createSessionServerPort(aServerId, aServerName);
	}
	public static DuplexRPCServerInputPort createSessionServerPort (String aServerId, String aServerName, String aLogicalServerName) {
		return factory.createSessionServerPort(aServerId, aServerName, aLogicalServerName );

	}

}
