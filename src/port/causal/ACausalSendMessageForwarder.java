package port.causal;

import inputport.datacomm.group.AnAbstractGroupSendTrapper;
import inputport.datacomm.group.GroupNamingSender;

import java.util.Set;

import util.trace.Tracer;


public class ACausalSendMessageForwarder extends AnAbstractGroupSendTrapper<Object, Object> {
//	GroupNamingSender<Object> destination;
	VectorTimeStamp myVectorTimeStamp;
	String myName;
	public ACausalSendMessageForwarder(GroupNamingSender<Object> aForwarder, 
			VectorTimeStamp aVectorTimeStamp,
			String aName) {
		super (aForwarder);	
		myVectorTimeStamp = aVectorTimeStamp;
		myName = aName;
		Tracer.info(this, "Adding to vector time stamp " + myName);
		myVectorTimeStamp.addUser(myName);
	}
	@Override
	public void send(Set<String> remoteNames, Object message) {
//		Message.info(this, this + " causually forwarding message " + message + " to " + remoteNames);
		myVectorTimeStamp.addMessage(myName);
		VectorTimeStampedMessage wrappedMessage = 
			new AVectorTimeStampedMessage(message, myVectorTimeStamp.clone());
		Tracer.info(this, this + " causually forwarding TS message " + wrappedMessage + " to " + remoteNames);
		destination.send(remoteNames, wrappedMessage);
	}

}
