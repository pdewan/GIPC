package multiserverport.datacomm.duplex.object;





import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPortFactory;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;






public class ObjectDuplexMultiServerClientPortSelector  {
//	static DuplexSessionPortFactory<Object> inputPortFactory = new ALayeredObjectDuplexSessionPortFactory();
	static DuplexMultiServerClientPortFactory<Object> inputPortFactory = new AnObjectDuplexMultiServerClientPortFactory();

	public static void setDuplexMultiServerPortFactory(DuplexMultiServerClientPortFactory<Object> aFactory) {
		inputPortFactory = aFactory;
	}
	public static DuplexMultiServerClientPort<Object> createObjectDuplexMultiServerClientPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return inputPortFactory.createDuplexMultiServerClientPort(aServerList, anId, aName, aChoice);
	}
	
	
}
