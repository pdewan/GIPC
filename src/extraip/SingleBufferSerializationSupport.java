package extraip;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.List;

public interface SingleBufferSerializationSupport {

	public abstract ByteBuffer getInputBuffer();
	
	public void setInputBuffer(ByteBuffer newVal);
	

	public abstract ByteBuffer getOutputBuffer();

	public abstract List<Serializable> getSerializablesFromInputBuffer();
	
	public abstract List<Serializable> getSerializablesFromInputBuffer(int length);


	public abstract void putSerializableInOutputBuffer(Serializable object);

	public abstract void putSerializablesInOutputBuffer(Serializable[] objects);

}