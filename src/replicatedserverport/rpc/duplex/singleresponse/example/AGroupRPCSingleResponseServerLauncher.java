package replicatedserverport.rpc.duplex.singleresponse.example;

import port.PortLauncherSupport;
import port.sessionserver.ServerPortDescription;
import inputport.rpc.group.adder.example.AGroupRPCServerInputPortLauncher;
import replicatedserverport.rpc.groupserver.singleresponse.ASingleResponseReplicatedGroupServerLauncherSupport;

public  class AGroupRPCSingleResponseServerLauncher extends AGroupRPCServerInputPortLauncher{
	ServerPortDescription serverPortDescription;
	public AGroupRPCSingleResponseServerLauncher(String aServerName, String aServerPort) {
		 super(aServerName, aServerPort);
	}
	

	public  AGroupRPCSingleResponseServerLauncher (ServerPortDescription aServerPortDescription) {
		super (aServerPortDescription.getName(), aServerPortDescription.getID());
		serverPortDescription = aServerPortDescription;
	}
	protected PortLauncherSupport getReplicatedPortLauncherSupport() {
		return new ASingleResponseReplicatedGroupServerLauncherSupport();
	}


}
