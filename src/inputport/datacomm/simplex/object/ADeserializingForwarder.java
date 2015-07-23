package inputport.datacomm.simplex.object;

import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;

import java.nio.ByteBuffer;

import serialization.Serializer;
import serialization.SerializerPoolSelector;
import util.trace.Tracer;




public class ADeserializingForwarder extends AnAbstractReceiveTrapper<ByteBuffer, Object> {
	protected Serializer serializer;
	public ADeserializingForwarder(ReceiveNotifier aReceiveNotifier) {
		super (null, aReceiveNotifier);
		// does not need to register as listener of port, so null argument
		serializer = SerializerPoolSelector.createSerializerPool(null);
	}
	@Override
	public void notifyPortReceive(String remoteEnd, ByteBuffer message) {
		Tracer.info(this, " Deserializing received buffer:" + message + " from:" + remoteEnd);
		try {
			Object serializable = serializer.objectFromInputBuffer(message);
//			destination.notifyPortReceive(remoteEnd, serializable);	
			notifySerializable(remoteEnd, serializable);
		} catch (Exception e) {
			e.printStackTrace();
			// the receiver may be able to do something with the ByteBuffer
			destination.notifyPortReceive(remoteEnd, message);
		}
	}
	protected void notifySerializable(String remoteEnd, Object serializable) {
		destination.notifyPortReceive(remoteEnd, serializable);	
	}
}
