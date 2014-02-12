package replicatedserverport.rpc.duplex.singleresponse;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperFactory;
import inputport.rpc.duplex.ADuplexCallTrapperFactory;

// a new instance must be created for each send and receive trapper
public class AnUpstreamSingleResponseClientDuplexCallTrapperFactory extends ADuplexCallTrapperFactory
	implements TrapperFactory<Object, Object> {

	ClientMessagesManager clientMessagesManager;
	public AnUpstreamSingleResponseClientDuplexCallTrapperFactory(ClientMessagesManager aClientMessagesManager) {
		clientMessagesManager = aClientMessagesManager;
	}
	protected ClientMessagesManager getClientMessagesManager(InputPort anInputPort) {

			return clientMessagesManager;
	}
	@Override
	public SendTrapper<Object, Object> createSendTrapper(InputPort anInputPort,
			NamingSender<Object> aDestination) {

		
		SendTrapper<Object, Object> sendTrapper =  new ASingleResponseClientDuplexCallSendTrapper(anInputPort, aDestination);

		return sendTrapper;
		
	}

	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<Object> aReceiveNotifier) {
		ReceiveTrapper<Object, Object> superTrapper = super.createReceiveTrapper(anInputPort, aReceiveNotifier);
		return new AnUpstreamSingleResponseClientDuplexRelayedCallReceiveTrapper
				(anInputPort, superTrapper, getClientMessagesManager(anInputPort));
	}

}
