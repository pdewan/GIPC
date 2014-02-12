package inputport.datacomm.simplex.object;

import inputport.InputPort;
import inputport.datacomm.AnAbstractSendTrapper;
import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.buffer.SendRegistrar;

import java.nio.ByteBuffer;

import serialization.Serializer;
import serialization.SerializerPoolSelector;
import util.trace.Tracer;


public class ASerializingForwarder extends AnAbstractSendTrapper<Object, ByteBuffer> {
	protected Serializer serializer;
	InputPort inputPort;
	public ASerializingForwarder(InputPort anInputPort, NamingSender<ByteBuffer>  aDestination) {
		super(aDestination);
		inputPort = anInputPort;
		// needs to register as send listener of port
		serializer = SerializerPoolSelector.createSerializerPool((SendRegistrar) inputPort);

	}
	@Override
	public void send(String remoteName, Object message) {	
		Tracer.info(this, "Serializing and forwarding message " + message + " to " + remoteName);
		if (message instanceof ByteBuffer) {
			destination.send(remoteName, (ByteBuffer) message);
		} else {
			try {
				ByteBuffer bbMessage = serializer.outputBufferFromObject(message);		
				destination.send(remoteName, bbMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
