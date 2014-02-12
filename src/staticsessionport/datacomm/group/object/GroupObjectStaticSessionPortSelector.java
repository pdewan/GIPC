package staticsessionport.datacomm.group.object;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.group.GroupSessionPort;
import staticsessionport.datacomm.group.GroupStaticSessionPortFactory;
public class GroupObjectStaticSessionPortSelector  {
//	static GroupSessionPortFactory<Object> sessionPortFactory = new AnObjectGroupSessionLayeredPortFactory();
	static GroupStaticSessionPortFactory<Object> sessionPortFactory = new AGroupObjectStaticSessionPortFactory();

	public static void setGroupSessionPortFactory(GroupStaticSessionPortFactory<Object> aFactory) {
		sessionPortFactory = aFactory;
	}
	public static GroupSessionPort<Object> createObjectGroupStaticSessionPort(
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName, 
			String aLogicalRemoteEndPoint,
			ParticipantChoice aChoice){
		return sessionPortFactory.createGroupStaticSessionPort(aServerList, anId, aName, aLogicalRemoteEndPoint, aChoice);
	}	
}
