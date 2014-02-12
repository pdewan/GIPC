package serialization.simple;

import serialization.Serializer;
import serialization.SerializerFactory;

public class ASimpleSerializerFactory implements SerializerFactory {
	public Serializer createSerializer() {
		return new ASimpleSerializer();
//		return new ANonCopyingBufferSerializationSupport();
	}
}