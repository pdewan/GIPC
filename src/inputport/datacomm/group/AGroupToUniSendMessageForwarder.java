package inputport.datacomm.group;

import java.util.Collection;

import inputport.datacomm.NamingSender;
import util.trace.Tracer;


public class AGroupToUniSendMessageForwarder<InMessageType> extends AnAbstractGroupToUniSendTrapper<InMessageType, InMessageType>{
	public AGroupToUniSendMessageForwarder(NamingSender<InMessageType>  aDestination) {
		 super (aDestination);
	}
	@Override
	public void send(Collection<String> clientNames, InMessageType message) {
		Tracer.info(this, this + "group forwarding sent message:" + message +  " to: " + clientNames);
		for (String clientName: clientNames)
			destination.send(clientName,  message);
	}
//	@Override
//	public void send(String aRemoteEnd, InMessageType aMessage) {
//		destination.send(aRemoteEnd, aMessage);
//	}
}
