package serialization;

import java.nio.ByteBuffer;
import java.util.List;


public interface ListSerializer extends Serializer {
	ByteBuffer outputBufferFromObjects(Object[] objects);
	List<Object> objectsFromInputBuffer(ByteBuffer inputBuffer);

}
