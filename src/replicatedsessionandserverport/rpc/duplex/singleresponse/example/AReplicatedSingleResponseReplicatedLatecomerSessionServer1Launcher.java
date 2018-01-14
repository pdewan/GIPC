package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.ASingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.Server1Launcher;

public class AReplicatedSingleResponseReplicatedLatecomerSessionServer1Launcher implements Server1Launcher{
	
	
	public static void main (String args[]) {
//		OEFrame frame = ObjectEditor.edit(TraceableDisplayAndWaitManagerFactory.getTraceableDisplayAndPrintManager());
//		frame.setTitle(SERVER_1_DESCRIPTION.getName());
		(new ASingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();

	}
	

	

}
