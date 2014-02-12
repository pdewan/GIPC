package multiserverport.rpc.group;

import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.datacomm.group.object.ObjectGroupMultiServerPortSelector;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;

public class AGroupRPCMultiServerClientPortFactory  implements GroupRPCMultiServerClientPortFactory {

//	@Override
//	public DuplexRPCClientInputPort createRPCClientInputPort(String theServerId,
//			String theServerName, String theClientName) {
//		// TODO Auto-generated method stub
//		TypedDuplexClientInputPort typedClientInputPort = TypedDuplexInputPortSelector.createTypedDuplexClientInputPort (theServerId, theServerName, theClientName);
//		DuplexRPCClientInputPort rpcClientInputPort =  new ADuplexRPCClientInputPort(typedClientInputPort);
//		return rpcClientInputPort;
//	}



	@Override
	public GroupRPCMultiServerClientPort createGroupRPCMultiServerClientPort(
			SessionParticipantDescription[] remoteList, String anId, 
			String name, 
			ParticipantChoice aChoice) {
		GroupMultiServerClientPort<Object> typedServerInputPort = 
				ObjectGroupMultiServerPortSelector.createGroupMultiServerClientPort(remoteList, anId, name, aChoice);
		GroupRPCMultiServerClientPort rpcMultiServerClientPort = new AGroupRPCMultiServerClientPort(typedServerInputPort);
		return rpcMultiServerClientPort;
	}

}
