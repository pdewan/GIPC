package sessionport.datacomm.duplex.object.relayed;

import util.trace.Tracer;

public class AMessageWithSource implements MessageWithSource {
	Object message;
	String source;
	public AMessageWithSource(String aSource, Object aMessage) {
		source = aSource;
		message = aMessage;
		Tracer.info(this, "Constructed message with source:" + this);

	}
	public AMessageWithSource() {	
	}
	public Object getMessage() {
		return message;
	}
	public void setMessage(Object message) {
		this.message = message;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	
	public String toString() {
		return 
				getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) +
				"(" + source + ":" + message + ")";
	}

}
