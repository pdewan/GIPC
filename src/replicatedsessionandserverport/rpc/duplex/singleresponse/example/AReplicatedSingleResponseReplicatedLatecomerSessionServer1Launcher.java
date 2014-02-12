package replicatedsessionandserverport.rpc.duplex.singleresponse.example;

import replicatedserverport.rpc.group.flexibleresponse.flexible.example.ASingleReponseReplicatedLatecomerSessionServerLauncher;
import replicatedserverport.rpc.group.flexibleresponse.flexible.example.Server1Launcher;
import bus.uigen.OEFrame;
import bus.uigen.ObjectEditor;
import bus.uigen.trace.TraceableDisplayAndWaitManagerFactory;

public class AReplicatedSingleResponseReplicatedLatecomerSessionServer1Launcher implements Server1Launcher{
	
	
	public static void main (String args[]) {
		OEFrame frame = ObjectEditor.edit(TraceableDisplayAndWaitManagerFactory.getTraceableDisplayAndPrintManager());
		frame.setTitle(SERVER_1_DESCRIPTION.getName());
		(new ASingleReponseReplicatedLatecomerSessionServerLauncher(SERVER_1_DESCRIPTION)).launch();

	}
	

	

}
