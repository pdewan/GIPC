package inputport.datacomm.simplex.object;

import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.AnAbstractSendTrapper;
import inputport.datacomm.NamingSender;
import inputport.datacomm.simplex.buffer.SendRegistrar;
import serialization.Serializer;
import serialization.SerializerPoolSelector;
import util.trace.Tracer;
import util.trace.port.objects.ObjectSerializationFinished;
import util.trace.port.objects.ObjectSerializationInitiated;


public class ASerializingForwarder extends AnAbstractSendTrapper<Object, ByteBuffer> implements SerializingForwarder{
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
				ObjectSerializationInitiated.newCase(this, remoteName, message, serializer);
				ByteBuffer bbMessage = serializer.outputBufferFromObject(message);	
				ObjectSerializationFinished.newCase(this, remoteName,  bbMessage, message);

//				destination.send(remoteName, bbMessage);
				sendBuffer(remoteName, bbMessage);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	protected void sendBuffer(String remoteName, ByteBuffer bbMessage) {
		destination.send(remoteName, bbMessage);
	}
}
