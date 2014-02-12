package sessionport.datacomm.duplex.object;





import port.ParticipantChoice;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.datacomm.duplex.DuplexSessionPortFactory;
import sessionport.datacomm.duplex.object.relayed.ARelayingObjectDuplexSessionPortFactory;






public class ObjectDuplexSessionPortSelector  {
//	static DuplexSessionPortFactory<Object> inputPortFactory = new ALayeredObjectDuplexSessionPortFactory();
	static DuplexSessionPortFactory<Object> inputPortFactory = new ARelayingObjectDuplexSessionPortFactory();

	public static void setDuplexSessionPortFactory(DuplexSessionPortFactory<Object> theSessionPortFactory) {
		inputPortFactory = theSessionPortFactory;
	}
	public static DuplexSessionPort<Object> createObjectDuplexSessionPort(
			String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName, ParticipantChoice aJoinChoice) {
		return inputPortFactory.createDuplexSessionPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, aJoinChoice);
	}
	public static DuplexSessionPort<Object> createDuplexSessionPort(
//			SessionsServer aSessionsServer, 

			DuplexRPCClientInputPort aSessionsManagerClienPort,
			String aSessionName, 
			String anId, 
			String aName, ParticipantChoice aJoinChoice) {
		return inputPortFactory.createDuplexSessionPort(aSessionsManagerClienPort, aSessionName, anId, aName, aJoinChoice);
	}
	
}
