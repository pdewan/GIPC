package replicatedserverport.rpc.group.flexibleresponse.flexible.example;

import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.late.LatecomerSessionServerLauncher;

public  class AnEarliestReponseReplicatedLatecomerSessionServerLauncher extends LatecomerSessionServerLauncher{

	public  AnEarliestReponseReplicatedLatecomerSessionServerLauncher (ServerPortDescription aServerPortDescription) {
		super(aServerPortDescription.getID(), aServerPortDescription.getName(), SESSION_SERVER_NAME);
	}

}
