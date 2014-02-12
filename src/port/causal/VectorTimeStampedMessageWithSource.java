package port.causal;

public interface VectorTimeStampedMessageWithSource {

	public abstract VectorTimeStampedMessage getVectorTimeStampedMessage();

	public abstract void setVectorTimeStampedMessage(
			VectorTimeStampedMessage aMessageWithVectorTimeStamp);

	public abstract String getSource();

	public abstract void setSource(String aSource);

}