package replicatedserverport.rpc.group.flexibleresponse.flexible;

import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.late.ALatecomerSessionServerLauncher;

public  class AnEarliestReponseReplicatedLatecomerSessionServerLauncher extends ALatecomerSessionServerLauncher{

	public  AnEarliestReponseReplicatedLatecomerSessionServerLauncher (ServerPortDescription aServerPortDescription) {
		super(aServerPortDescription.getID(), aServerPortDescription.getName(), SESSION_SERVER_NAME);
	}

}
