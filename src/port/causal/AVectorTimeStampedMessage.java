package port.causal;

import util.trace.Tracer;


public class AVectorTimeStampedMessage implements VectorTimeStampedMessage {
	Object message;
	VectorTimeStamp vectorTimeStamp;
	public AVectorTimeStampedMessage (Object theMessage, VectorTimeStamp theVectorTimeStamp) {
		message = theMessage;
		vectorTimeStamp = theVectorTimeStamp;
		Tracer.info(this, "New Vector Time Stamp of size:" + vectorTimeStamp.size());
	}	
	public Object getMessage() {
		return message;
	}	
	public VectorTimeStamp getVectorTimeStamp() {
		return vectorTimeStamp;
	}
	@Override
	public String toString() {
		return super.toString() + " message " + message + " time stamp " + vectorTimeStamp;
	}
	
	public AVectorTimeStampedMessage () {		
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public void setVectorTimeStamp(VectorTimeStamp vectorTimeStamp) {
		this.vectorTimeStamp = vectorTimeStamp;
	}	

}
