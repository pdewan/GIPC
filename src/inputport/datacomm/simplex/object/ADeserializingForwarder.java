package inputport.datacomm.simplex.object;

import java.nio.ByteBuffer;

import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import serialization.Serializer;
import serialization.SerializerPoolSelector;
import util.trace.Tracer;
import util.trace.port.objects.BufferDeserializationFinished;
import util.trace.port.objects.BufferDeserializationInitiated;




public class ADeserializingForwarder extends AnAbstractReceiveTrapper<ByteBuffer, Object> implements DeserializingForwarder {
	protected Serializer serializer;
	public ADeserializingForwarder(ReceiveNotifier aReceiveNotifier) {
		super (null, aReceiveNotifier);
		// does not need to register as listener of port, so null argument, why not as listener, need to return buffer?
		serializer = SerializerPoolSelector.createSerializerPool(null);
	}
	@Override
	public void notifyPortReceive(String remoteEnd, ByteBuffer message) {
		Tracer.info(this, " Deserializing received buffer:" + message + " from:" + remoteEnd);
		try {
			BufferDeserializationInitiated.newCase(this, remoteEnd, message, serializer);
			Object serializable = serializer.objectFromInputBuffer(message);
			if (serializable == null) {
				System.err.println("Forwarding to receive listener unserializable byte buffer:" + message);
				destination.notifyPortReceive(remoteEnd, message);
				return;
			}
			BufferDeserializationFinished.newCase(this, remoteEnd, message, serializable);
//			destination.notifyPortReceive(remoteEnd, serializable);	
			notifySerializable(remoteEnd, serializable);
		} catch (Throwable e) {
			e.printStackTrace();
			// the receiver may be able to do something with the ByteBuffer
//			destination.notifyPortReceive(remoteEnd, message);
		}
	}
	protected void notifySerializable(String remoteEnd, Object serializable) {
		destination.notifyPortReceive(remoteEnd, serializable);	
	}
}
