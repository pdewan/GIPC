package replicatedserverport.rpc.duplex.singleresponse;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperFactory;
import inputport.rpc.duplex.ADuplexCallTrapperFactory;

// a new instance must be created for each send and receive trapper
public class ADownstreamSingleResponseClientDuplexCallTrapperFactory extends ADuplexCallTrapperFactory
	implements TrapperFactory<Object, Object> {
//	SendTrapper<Object, Object> sendTrapper;
//	ReceiveTrapper<Object, Object> receiveTrapper;
	ClientMessagesManager clientMessagesManager;
	public ADownstreamSingleResponseClientDuplexCallTrapperFactory(ClientMessagesManager aClientMessagesManager) {
		clientMessagesManager = aClientMessagesManager;
	}
	protected ClientMessagesManager getClientMessagesManager(InputPort anInputPort) {
//		if (clientMessagesManager == null) 
//			clientMessagesManager =  ClientMessagesManagerSelector.createClientMessagesManager(anInputPort);
		
			return clientMessagesManager;
	}
	@Override
	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
			NamingSender<Object> aDestination) {

		
		// not sure we need to make this ASingleReponseSendTrapper as this is the downstream one
		SendTrapper<Object, Object> sendTrapper =  new ASingleResponseClientDuplexCallSendTrapper(anInputPort, aDestination);

//		getClientMessagesManager(anInputPort).setSendTrapper(sendTrapper);
		return sendTrapper;
		
	}

	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<Object> aReceiveNotifier) {
		ReceiveTrapper<Object, Object> superTrapper = super.createReceiveTrapper(anInputPort, aReceiveNotifier);
		return new ADownstreamSingleResponseClientDuplexRelayedCallReceiveTrapper
				(anInputPort, superTrapper, getClientMessagesManager(anInputPort));
	}

}
