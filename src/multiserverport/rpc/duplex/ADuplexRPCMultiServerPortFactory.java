package multiserverport.rpc.duplex;

import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import multiserverport.datacomm.duplex.object.ObjectDuplexMultiServerClientPortSelector;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public class ADuplexRPCMultiServerPortFactory implements DuplexRPCMultiServerPortFactory {	
	
	
	@Override
	public DuplexRPCMultiServerClientPort createDuplexRPCMultiServerClientPort(
			SessionParticipantDescription[] aServerList, String anId, String aName, 
			ParticipantChoice aChoice) {
		DuplexMultiServerClientPort<Object> duplexPort = 
				ObjectDuplexMultiServerClientPortSelector.createObjectDuplexMultiServerClientPort(aServerList, anId, aName, aChoice);
		return new ADuplexRPCMultiServerClientPort(duplexPort);
	}
}
