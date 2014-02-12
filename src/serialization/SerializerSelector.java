package serialization;
//import serialization.custom.ACustomSerializerFactory;
import serialization.simple.ASimpleSerializerFactory;
import util.trace.Tracer;
public class SerializerSelector {
//	static BufferSerializationSupportFactory factory = new ANonCopyingBufferSerializationSupportFactory();
//	static SerializerFactory factory = new ACustomSerializerFactory();
	static SerializerFactory factory = new ASimpleSerializerFactory();
	public static SerializerFactory getSerializerFactory() {
		return factory;
	}
	public static void setSerializerFactory(SerializerFactory newVal) {
		Tracer.info(SerializerSelector.class, "Setting serializer:" + newVal);

		 factory = newVal;
	}
	public static Serializer createSerializer() {
		return factory.createSerializer();
	}
}
