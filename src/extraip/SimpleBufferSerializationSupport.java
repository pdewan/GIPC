package extraip;

import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.List;

public interface SimpleBufferSerializationSupport {

//	public abstract ByteBuffer getInputBuffer();
	
//	public void setInputBuffer(ByteBuffer newVal);
	

	public abstract ByteBuffer getOutputBuffer();
	
	public List<Serializable> serializablesFromInputBuffer(ByteBuffer inputBuffer) ;

//	public abstract List<Serializable> getSerializablesFromInputBuffer();

	public abstract void putSerializableInOutputBuffer(Serializable object);

	public abstract void putSerializablesInOutputBuffer(Serializable[] objects);

}