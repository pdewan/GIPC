package sessionport.datacomm.duplex.object.relayed;


import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.ParticipantChoice;
import sessionport.datacomm.duplex.AnAbstractDuplexSessionPortFactory;
import sessionport.datacomm.duplex.DuplexSessionPort;



public class ARelayingObjectDuplexSessionPortFactory 
		extends AnAbstractDuplexSessionPortFactory<Object>  {
	

	@Override
	public DuplexSessionPort<Object> createDuplexSessionPort(
			DuplexRPCClientInputPort aSessionServerClientPort, String sessionName, String anId,
			String name, ParticipantChoice aJoinChoice) {
		return new ARelayingObjectDuplexSessionPort(aSessionServerClientPort, sessionName, anId, name, aJoinChoice);
	}

}
