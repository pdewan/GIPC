package extraip;

import java.nio.ByteBuffer;

public interface CopyOfSendDescription {
	ByteBuffer getData();
	void setData(ByteBuffer newVal);
	public String getDestination();
	public void setDestination(String destintation);
}
