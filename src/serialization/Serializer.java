package serialization;
import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
public interface Serializer {
	public Object objectFromInputBuffer(ByteBuffer inputBuffer) throws StreamCorruptedException ;
	public ByteBuffer  outputBufferFromObject(Object object) throws NotSerializableException;
}