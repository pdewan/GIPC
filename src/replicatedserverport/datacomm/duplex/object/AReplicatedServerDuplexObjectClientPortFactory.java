package replicatedserverport.datacomm.duplex.object;

import inputport.datacomm.duplex.DuplexClientInputPort;
import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.datacomm.group.object.ObjectGroupMultiServerPortSelector;
import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import replicatedserverport.datacomm.duplex.AReplicatedServerDuplexClientPort;
import replicatedserverport.datacomm.duplex.ReplicatedServerDuplexClientPortFactory;

public class AReplicatedServerDuplexObjectClientPortFactory 
	implements ReplicatedServerDuplexClientPortFactory<Object >{

	@Override
	public DuplexClientInputPort<Object> createDuplexReplicatedServerClientInputPort(
			SessionParticipantDescription[] aServerList, String anId, String aName, ParticipantChoice aChoice) {
		GroupMultiServerClientPort<Object> multiServerPort = 
			ObjectGroupMultiServerPortSelector.createGroupMultiServerClientPort(aServerList, anId, aName, aChoice);
		return new AReplicatedServerDuplexClientPort<Object>(multiServerPort, anId);
	}

}
