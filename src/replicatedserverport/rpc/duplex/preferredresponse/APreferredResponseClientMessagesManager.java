package replicatedserverport.rpc.duplex.preferredresponse;

import inputport.InputPort;
import port.common.DistMisc;
import replicatedserverport.rpc.duplex.fixedresponse.ClientServerMapping;
import replicatedserverport.rpc.duplex.singleresponse.ASingleResponseClientMessagesManager;
import replicatedserverport.rpc.duplex.singleresponse.ClientMessagesManager;
import util.trace.Tracer;


public class APreferredResponseClientMessagesManager 
			extends  ASingleResponseClientMessagesManager 
			implements ClientMessagesManager{
//	InputPort inputPort;
	String myName;
	String myPreferredServer;
	
	public APreferredResponseClientMessagesManager(String aName) {
//		inputPort = anInputPort;
//		myName = inputPort.getLocalName();
		myName = aName;
		myPreferredServer = ClientServerMapping.getServer(myName);
		
	}
	
//	public void setName(String aName) {
//		myName = aName;
//	}
	
	protected boolean setNewServer() {
		if (servers.size() == 0) {
			Tracer.error("Lost connection to all servers");
			return false;
		}	
		String oldServer = currentServer;
		if (servers.contains(myPreferredServer))
			currentServer = myPreferredServer;
		else {
			int randomIndex = DistMisc.random(0, servers.size());
			currentServer = servers.get(randomIndex);
		}
		return !currentServer.equals(oldServer);
	}
		
}
