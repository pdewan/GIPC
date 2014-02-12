package staticsessionport.rpc.duplex;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.rpc.duplex.DuplexRPCSessionPort;

public class DuplexRPCStaticSessionPortSelector {
	static DuplexRPCStaticSessionPortFactory factory = new ADuplexRPCStaticSessionPortFactory();
	public static void setStaticSessionPortFactory(DuplexRPCStaticSessionPortFactory theSessionPortFactory) {
		factory = theSessionPortFactory;
	}
	
	public static DuplexRPCSessionPort createDuplexRPCStaticSessionPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return factory.createDuplexRPCSessionPort(aServerList, anId, aName, aChoice);			
	}
}
