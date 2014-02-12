package port.sessionserver.relay.late.mvc.example;

import inputport.InputPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.util.List;

import port.sessionserver.relay.mvc.example.AnMVCServerConnectionListenerAndLauncher;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.trace.Tracer;
import examples.mvc.local.duplex.ADuplexFrostyAWTGUI;
import examples.mvc.local.duplex.ADuplexFrostyConsoleUI;
import examples.mvc.local.duplex.ADuplexFrostyVerticalGUI;
import examples.mvc.local.duplex.DuplexFrostyModel;
import examples.mvc.local.simplex.FrostyConsoleInteractor;

public class ALatecomerMVCServerAndLauncherConnectionListener extends AnMVCServerConnectionListenerAndLauncher{	
	List<MessageWithSource> messageList;
	public ALatecomerMVCServerAndLauncherConnectionListener (DuplexRPCClientInputPort aRelayerPort, List<MessageWithSource> aMessageList) {
		super (aRelayerPort);
		messageList = aMessageList;
	}
	protected void createUI(InputPort anInputPort) {
		DuplexFrostyModel clientModel = getFrostyModel();	
		(new ADuplexFrostyAWTGUI()).interact(clientModel);
		(new ADuplexFrostyVerticalGUI()).interact(clientModel);
		FrostyConsoleInteractor frostyInteractor =  new ADuplexFrostyConsoleUI();
		frostyInteractor.interact(clientModel);
		processMessages(messageList); // at this point all listeners have been added
		frostyInteractor.processConsoleInput(); // infinite loop so cannot process mesages after this
	}	
	protected void processMessages (List<MessageWithSource> messages) {
		Tracer.info(this, "Process messages");
		for (MessageWithSource message:messages) {
			Tracer.info(this, "Process message:" + message);
			relayerPort.notifyPortReceive(mvcClientPort.getLogicalRemoteEndPoint(), message);
		}
	}	
}
