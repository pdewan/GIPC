package replicatedserverport.rpc.duplex.singleresponse.example;

import util.trace.port.ConnectionEventManagerFactory;
import bus.uigen.ObjectEditor;

public class AliceSingleResponseGroupRPCClientPortLauncher {
	public static void main(String[] args) {
		ObjectEditor.edit((ConnectionEventManagerFactory.getConnectionManager()));

		(new ASingleResponseGroupRPCDuplexClientPortLauncher("Alice")).launch();		

	}


}
