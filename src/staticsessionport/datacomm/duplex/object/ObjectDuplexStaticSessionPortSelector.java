package staticsessionport.datacomm.duplex.object;





import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.duplex.DuplexSessionPort;
import staticsessionport.datacomm.duplex.DuplexStaticSessionPortFactory;






public class ObjectDuplexStaticSessionPortSelector  {
//	static DuplexSessionPortFactory<Object> inputPortFactory = new ALayeredObjectDuplexSessionPortFactory();
	static DuplexStaticSessionPortFactory<Object> inputPortFactory = new AnObjectDuplexStaticSessionPortFactory();

	public static void setDuplexSessionPortFactory(DuplexStaticSessionPortFactory<Object> theSessionPortFactory) {
		inputPortFactory = theSessionPortFactory;
	}
	public static DuplexSessionPort<Object> createObjectDuplexStaticSessionPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return inputPortFactory.createDuplexStaticSessionPort(aServerList, anId, aName, null, aChoice);
	}
	public static DuplexSessionPort<Object> createDuplexStaticSessionPort(
			SessionParticipantDescription[] aServerList, String anId,
			String aName,
			ParticipantChoice aChoice) {
		return inputPortFactory.createDuplexStaticSessionPort(aServerList, anId, aName, null, aChoice);
	}
	
}
