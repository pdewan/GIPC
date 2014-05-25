package inputport.datacomm.group;

import java.util.Set;

import util.trace.Tracer;


public class AGroupSendMessageForwarder<InMessageType> extends AnAbstractGroupSendTrapper<InMessageType, InMessageType>{
	public AGroupSendMessageForwarder(GroupNamingSender<InMessageType>  aDestination) {
		 super (aDestination);
	}
	@Override
	public void send(Set<String> clientNames, InMessageType message) {
		Tracer.info(this, this + "group forwarding sent message:" + message +  " to: " + clientNames);
		destination.send(clientNames,  message);
	}
	@Override
	public void send(String clientName, InMessageType message) {
//		Tracer.info(this, "Converting single to multiple send");
//		Set<String> clientNames = new HashSet();
//		clientNames.add(clientName);
		destination.send (clientName, message);
		
	}
//	@Override
//	public void send(String aRemoteEnd, InMessageType aMessage) {
//		destination.send(aRemoteEnd, aMessage);
//	}
}
