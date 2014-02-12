package replicatedserverport.rpc.duplex.singleresponse;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperFactory;

public class ASingleResponseServerDuplexTrapperFactory implements TrapperFactory<Object, Object>{
	protected ServerMessagesManager serverMessagesManager;
	public ASingleResponseServerDuplexTrapperFactory() {
		serverMessagesManager = createServerMessageManager();
	}
	protected ServerMessagesManager createServerMessageManager() {
		return new AServerMessagesManager();
	}
	@Override
	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
			NamingSender<Object> destination) {
		ASingleResponseServerDuplexSendTrapper retVal = new ASingleResponseServerDuplexSendTrapper(anInputPort, destination,  serverMessagesManager);
		serverMessagesManager.setBufferedMessageSender(retVal);
		anInputPort.addConnectionListener(serverMessagesManager);
		return retVal;
	}

	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<Object> receiveNotifier) {
		return new ASingleResponseServerDuplexReceiveTrapper(anInputPort, receiveNotifier, serverMessagesManager);
	}
}
