package multiserverport.datacomm.group.object;
import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.datacomm.group.GroupMultiServerClientPortFactory;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
public class ObjectGroupMultiServerPortSelector  {
//	static GroupSessionPortFactory<Object> sessionPortFactory = new AnObjectGroupSessionLayeredPortFactory();
	static GroupMultiServerClientPortFactory<Object> factory = new AnObjectGroupMultiServerPortFactory();

	public static void setGroupMultiServerClientPortFactory(GroupMultiServerClientPortFactory<Object> aFactory) {
		factory = aFactory;
	}
	
	
	public static GroupMultiServerClientPort<Object> createGroupMultiServerClientPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName,
			ParticipantChoice aChoice) {
		return factory.createGroupMultiServerClientPort(aServerList, anId, aName, aChoice);
	}
}
