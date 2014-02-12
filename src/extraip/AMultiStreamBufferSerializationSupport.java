package extraip;

import java.util.HashMap;
import java.util.Map;

import serialization.Serializer;
import serialization.simple.ASimpleSerializer;


public class AMultiStreamBufferSerializationSupport implements MultiStreamBufferSerializationSupport {
	Map<String, Serializer> remoteEndToBufferSerializationSupport = new HashMap();

	@Override
	public Serializer bufferSerializationSupport(
			String streamName) {
		Serializer retVal = remoteEndToBufferSerializationSupport.get(streamName);
		if (retVal == null) {
			retVal = new ASimpleSerializer();
			remoteEndToBufferSerializationSupport.put(streamName, retVal);
		}
		return retVal;	
	}

}
