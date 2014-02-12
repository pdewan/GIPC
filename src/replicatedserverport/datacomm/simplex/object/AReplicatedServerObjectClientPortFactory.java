package replicatedserverport.datacomm.simplex.object;

import port.ParticipantChoice;
import port.sessionserver.SessionParticipantDescription;
import inputport.datacomm.simplex.SimplexClientInputPort;
import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.datacomm.group.object.ObjectGroupMultiServerPortSelector;
import replicatedserverport.datacomm.simplex.AReplicatedServerClientPort;
import replicatedserverport.datacomm.simplex.ReplicatedServerClientPortFactory;

public class AReplicatedServerObjectClientPortFactory 
	implements ReplicatedServerClientPortFactory<Object >{

	@Override
	public SimplexClientInputPort<Object> createReplicatedServerClientInputPort(
			SessionParticipantDescription[] aServerList, String anId, String aName, ParticipantChoice aChoice) {
		GroupMultiServerClientPort<Object> multiServerPort = 
			ObjectGroupMultiServerPortSelector.createGroupMultiServerClientPort(aServerList, anId, aName, aChoice);
		return new AReplicatedServerClientPort<Object>(multiServerPort, anId);
	}

}
