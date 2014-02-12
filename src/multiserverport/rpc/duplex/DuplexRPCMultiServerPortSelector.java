package multiserverport.rpc.duplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public class DuplexRPCMultiServerPortSelector {
	static DuplexRPCMultiServerPortFactory factory = new ADuplexRPCMultiServerPortFactory();
	public static void setDuplexRPCMultiServerClientPortFactory(DuplexRPCMultiServerPortFactory theFactory) {
		factory = theFactory;
	}	
	public DuplexRPCMultiServerClientPort createDuplexRPCMultiServerPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return factory.createDuplexRPCMultiServerClientPort(aServerList, anId, aName, aChoice);
	}
}
