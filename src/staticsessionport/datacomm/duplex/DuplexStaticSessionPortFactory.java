package staticsessionport.datacomm.duplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;

public interface DuplexStaticSessionPortFactory<MessageType> {
	DuplexSessionPort<MessageType> createDuplexStaticSessionPort(
										SessionParticipantDescription[] aRemoteList, 
										String anId,
										String aName, 
										String aRemoteEndPoint,
										ParticipantChoice aChoice);	
	

}
