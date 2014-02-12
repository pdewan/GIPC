package replicatedserverport.rpc.groupserver.singleresponse;


import port.sessionserver.ASessionsServerCreator;
import port.sessionserver.relay.ARelayerSupportingSessionsServerCreator;
import port.sessionserver.relay.late.ALatecomerRelayerAndSessionsServerCreator;
import inputport.rpc.duplex.DuplexRPCServerInputPort;
import inputport.rpc.group.GroupRPCServerInputPort;
import replicatedserverport.rpc.duplex.singleresponse.DuplexSingleResponseUtlity;
import replicatedserverport.rpc.duplex.singleresponse.SingleResponseReplicatedClientServerUtlity;

public class ASingleResponseSessionServerCreator {
	public static DuplexRPCServerInputPort createSingleResponseDuplexSessionServer(String aServerId, String aServerName, String aLogicalServerName) {
		DuplexRPCServerInputPort serverInputPort = ASessionsServerCreator.createSessionsServer(aServerId, aServerName, aLogicalServerName);
		DuplexSingleResponseUtlity.supportSingleResponse(serverInputPort);
		return serverInputPort;		
	}
	
	public static DuplexRPCServerInputPort createSingleResponseDuplexRelayerSupportngSessionServer(String aServerId, String aServerName, String aLogicalServerName) {
		DuplexRPCServerInputPort serverInputPort = ARelayerSupportingSessionsServerCreator.createRelayerSupportingSessionsServer(aServerId, aServerName, aLogicalServerName);
		DuplexSingleResponseUtlity.supportSingleResponse(serverInputPort);
		return serverInputPort;		
	}
	public static GroupRPCServerInputPort createSingleResponseLatecomerSessionsServerAndRelayer(String aServerId, String aServerName, String aLogicalServerName) {
		GroupRPCServerInputPort serverInputPort = ALatecomerRelayerAndSessionsServerCreator.createLatecomerSessionsServerAndRelayer(aServerId, aServerName, aLogicalServerName);			
		SingleResponseReplicatedClientServerUtlity.supportSingleResponse(serverInputPort);
		return serverInputPort;		
	}

}
