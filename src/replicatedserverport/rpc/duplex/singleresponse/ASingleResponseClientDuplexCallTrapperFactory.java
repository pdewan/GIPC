package replicatedserverport.rpc.duplex.singleresponse;

import util.misc.HashIdentityMap;
import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperFactory;
import inputport.rpc.duplex.ADuplexCallTrapperFactory;

// a new instance must be created for each send and receive trapper
// this does not really happem as there is a single static class to 
// which one of these is assigned
// may have more than one replicated port
// so let us create a table of client messengers
public class ASingleResponseClientDuplexCallTrapperFactory extends ADuplexCallTrapperFactory
	implements TrapperFactory<Object, Object> {
//	SendTrapper<Object, Object> sendTrapper;
//	ReceiveTrapper<Object, Object> receiveTrapper;
//	ClientMessagesManager clientMessagesManager;
	HashIdentityMap<InputPort, ClientMessagesManager> portToClientMessageManager = 
			new HashIdentityMap();
	public ASingleResponseClientDuplexCallTrapperFactory() {
		
	}
	protected ClientMessagesManager getClientMessagesManager(InputPort anInputPort) {
		ClientMessagesManager retVal = portToClientMessageManager.get(anInputPort);
		if (retVal == null) {
			retVal = ClientMessagesManagerSelector.createClientMessagesManager();
			portToClientMessageManager.put(anInputPort, retVal);
		}
		return retVal;
	}
	@Override
	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
			NamingSender<Object> aDestination) {
//		return (SendTrapper) destination; // why add a forwarding node
//		SendTrapper<Object, Object> sendTrapper =  super.createSendTrapper(anInputPort, aDestination);
		SendTrapper<Object, Object> sendTrapper =  new ASingleResponseClientDuplexCallSendTrapper(anInputPort, aDestination);

		getClientMessagesManager(anInputPort).setSendTrapper(sendTrapper);
		return sendTrapper;
		
	}
//	@Override
//	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
//			NamingSender<Object> destination) {
////		return (SendTrapper) destination; // why add a forwarding node
//		sendTrapper =  new ASendMessageForwarder<Object>(destination);
//		clientMessagesManager.setSendTrapper(sendTrapper);
//		return sendTrapper;
//		
//	}

//	@Override
//	public ReceiveTrapper<Object, Object> createReceiveTrapper(
//			InputPort anInputPort, ReceiveNotifier<Object> aReceiveNotifier) {
//		ReceiveTrapper<Object, Object> superTrapper = super.createReceiveTrapper(anInputPort, aReceiveNotifier);
//		return new ASingleResponseClientReceiveTrapper(anInputPort, aReceiveNotifier, clientMessagesManager);
//	}
	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<Object> aReceiveNotifier) {
		ReceiveTrapper<Object, Object> superTrapper = super.createReceiveTrapper(anInputPort, aReceiveNotifier);
		return new ASingleResponseClientDuplexCallReceiveTrapper(anInputPort, superTrapper, getClientMessagesManager(anInputPort));
	}

}
