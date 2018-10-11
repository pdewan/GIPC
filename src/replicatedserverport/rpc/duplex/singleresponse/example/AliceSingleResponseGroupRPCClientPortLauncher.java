package replicatedserverport.rpc.duplex.singleresponse.example;

import bus.uigen.ObjectEditor;
import util.trace.port.ConnectionEventManagerFactory;

public class AliceSingleResponseGroupRPCClientPortLauncher {
	public static void main(String[] args) {
		ObjectEditor.edit((ConnectionEventManagerFactory.getConnectionManager()));

		(new ASingleResponseGroupRPCDuplexClientPortLauncher("Alice")).launch();		

	}


}
