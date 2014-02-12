package port.causal;

public class AVectorTimeStampedMessageWithSource implements VectorTimeStampedMessageWithSource {
	VectorTimeStampedMessage messageWithVectorTimeStamp;
	String source;
	public AVectorTimeStampedMessageWithSource(String aSource, VectorTimeStampedMessage aMessageWithVectorTimeStamp) {
		messageWithVectorTimeStamp = aMessageWithVectorTimeStamp;
		source = aSource;
	}
	
	public VectorTimeStampedMessage getVectorTimeStampedMessage() {
		return messageWithVectorTimeStamp;
	}
	
	public void setVectorTimeStampedMessage(
			VectorTimeStampedMessage aMessageWithVectorTimeStamp) {
		messageWithVectorTimeStamp = aMessageWithVectorTimeStamp;
	}
	
	public String getSource() {
		return source;
	}
	
	public void setSource(String aSource) {
		source = aSource;
	}
	
	@Override
	public String toString() {
		return super.toString() + " TS message " + messageWithVectorTimeStamp + " source " + source;
	}
	
	public AVectorTimeStampedMessageWithSource() { }

	public void setMessageWithVectorTimeStamp(
			VectorTimeStampedMessage messageWithVectorTimeStamp) {
		this.messageWithVectorTimeStamp = messageWithVectorTimeStamp;
	}
	
		
	
	
}
