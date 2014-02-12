package staticsessionport.rpc.group;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.rpc.group.AGroupRPCSessionPort;
import sessionport.rpc.group.GroupRPCSessionPort;
import staticsessionport.datacomm.group.object.GroupObjectStaticSessionPortSelector;

public class AGroupRPCStaticSessionPortFactory implements GroupRPCStaticSessionPortFactory {

	@Override
	public GroupRPCSessionPort createGroupRPCStaticSessionPort (
			SessionParticipantDescription[] aServerList, 
			String anId,
			String aName, 
			String aLogicalRemoteEndPoint,
			ParticipantChoice aChoice) {
//		setFactories();
		GroupSessionPort<Object> objectSessionPort = 
				GroupObjectStaticSessionPortSelector.createObjectGroupStaticSessionPort(aServerList, anId, aName, aLogicalRemoteEndPoint, aChoice);
		return new AGroupRPCSessionPort(objectSessionPort); 
	}
	
//	protected void setFactories() {
//		ADuplexRPCInputPortLauncherSupport.setDuplexTrapperFactories();
//		ADuplexRPCInputPortLauncherSupport.setDuplexCallInvokerCompleterFactories();
//		ADuplexRPCInputPortLauncherSupport.setSimplexMarshallerfactory();
//	}

}
