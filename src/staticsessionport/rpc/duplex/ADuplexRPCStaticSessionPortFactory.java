package staticsessionport.rpc.duplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import sessionport.rpc.duplex.ADuplexRPCSessionPort;
import sessionport.rpc.duplex.DuplexRPCSessionPort;
import staticsessionport.datacomm.duplex.object.ObjectDuplexStaticSessionPortSelector;

public class ADuplexRPCStaticSessionPortFactory implements DuplexRPCStaticSessionPortFactory {	
	public DuplexRPCSessionPort createDuplexRPCSessionPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		DuplexSessionPort<Object> objectDuplexSessionPort = ObjectDuplexStaticSessionPortSelector.createObjectDuplexStaticSessionPort(
				aServerList, anId, aName, aChoice);
		return new ADuplexRPCSessionPort(objectDuplexSessionPort);		
	}
}
