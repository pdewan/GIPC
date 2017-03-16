package examples.gipc.counter.customization;

import serialization.Serializer;
import serialization.SerializerFactory;

public class ACustomSerializerFactory implements SerializerFactory {
	public Serializer createSerializer() {
		return new ACustomSerializer();
	}
}