package replicatedserverport.rpc.duplex.fixedresponse;

import inputport.InputPort;
import inputport.datacomm.AnAbstractSendTrapper;
import inputport.datacomm.NamingSender;


public class AFixedResponseServerDuplexSendTrapper extends AnAbstractSendTrapper<Object, Object> 
	{
	InputPort inputPort;
	public AFixedResponseServerDuplexSendTrapper(InputPort anInputPort, NamingSender<Object> aDestination) {
		super(aDestination);
		inputPort = anInputPort;
	}	

	@Override
	public synchronized void send(String aClientName, Object aMessage) {
//		if (aClientName.equals(inputPort.getLocalName()))
		if (ClientServerMapping.isServer(aClientName, inputPort.getLocalName()))
			destination.send(aClientName, aMessage);
	} 

}
