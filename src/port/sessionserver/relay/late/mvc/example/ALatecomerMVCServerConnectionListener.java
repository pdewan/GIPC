package port.sessionserver.relay.late.mvc.example;

import inputport.ConnectionType;
import inputport.datacomm.ReceiveListener;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.util.List;

import port.relay.mvc.example.GenericRelayingCollaborativeFrostyModel;
import port.sessionserver.relay.mvc.example.AnMVCServerConnectionListener;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.session.MessageReceiver;
import util.trace.Tracer;

public class ALatecomerMVCServerConnectionListener extends AnMVCServerConnectionListener{	
	List<MessageWithSource> messageList;
	DuplexRPCClientInputPort relayerPort;
	ReceiveListener sessionJoiner;
	public ALatecomerMVCServerConnectionListener (DuplexRPCClientInputPort aRelayerPort, GenericRelayingCollaborativeFrostyModel aModel,
			List<MessageWithSource> aMessageList, ReceiveListener aSessionJoiner) {
		super (aModel);
		messageList = aMessageList;
		relayerPort = aRelayerPort;
		sessionJoiner = aSessionJoiner;
	}
	@Override
	// executed by selectiom thread
	public void connected(String aRemoteEnd, ConnectionType aConnectionType) {
		super.connected(aRemoteEnd, aConnectionType);
		// model has all of the proxies it needs and they are ready to process messages		
		// all listeners have been added
		processMessages(messageList); 
	}
//	protected void createUI(InputPort anInputPort) {
//		DuplexFrostyModel clientModel = getFrostyModel();	
//		(new ADuplexFrostyAWTGUI()).interact(clientModel);
//		(new ADuplexFrostyVerticalGUI()).interact(clientModel);
//		FrostyConsoleInteractor frostyInteractor =  new ADuplexFrostyConsoleUI();
//		frostyInteractor.interact(clientModel);
//		processMessages(messageList); // at this point all listeners have been added
//		frostyInteractor.processConsoleInput(); // infinite loop so cannot process mesages after this
//	}	
	protected  void processMessages (List<MessageWithSource> messages) {
		relayerPort.addReceiveListener(model);		
		relayerPort.removeReceiveListener(sessionJoiner);
		Tracer.info(this, "Process messages");
		for (MessageWithSource message:messages) {
//			System.out.println("processing messages");
			Tracer.info(this, "Process message:" + message);
			relayerPort.notifyPortReceive(((DuplexRPCClientInputPort) inputPort).getLogicalRemoteEndPoint(), message);
		}		
	}	
}
