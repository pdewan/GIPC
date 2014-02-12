package port.ot;

import java.io.Serializable;

public interface OTTimeStamp extends Serializable {
   public abstract int getLocalCount();
	
	public void setLocalCount(int newVal);
	
	public abstract int getRemoteCount();
	
	public void setRemoteCount(int newVal);
	
	public boolean isConcurrent(OTTimeStamp other);
	public boolean isGreaterThanOrEqual(OTTimeStamp other);
	
	public void incrementLocalCount();
	
	public void incrementRemoteCount();
	public OTTimeStamp copy();

}
