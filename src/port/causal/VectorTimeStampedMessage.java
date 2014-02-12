package port.causal;

import java.io.Serializable;

public interface VectorTimeStampedMessage extends Serializable {

	public abstract Object getMessage();

	public abstract VectorTimeStamp getVectorTimeStamp();

}