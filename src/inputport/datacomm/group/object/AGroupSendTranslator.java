package inputport.datacomm.group.object;

import inputport.InputPort;
import inputport.datacomm.group.AnAbstractGroupSendTrapper;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.simplex.buffer.SendRegistrar;

import java.nio.ByteBuffer;
import java.util.Set;

import serialization.Serializer;
import serialization.SerializerPoolSelector;
import util.trace.Tracer;





public class AGroupSendTranslator extends AnAbstractGroupSendTrapper<Object, ByteBuffer> {
	protected Serializer bufferSerializationSupport;
	InputPort inputPort;
	public AGroupSendTranslator(InputPort anInputPort, GroupNamingSender<ByteBuffer>  aDestination) {
		super(aDestination);
		bufferSerializationSupport = SerializerPoolSelector.createSerializerPool((SendRegistrar) inputPort);
	}
	@Override
	public void send(Set<String> clientNames, Object message) {
		Tracer.info(this, this + " group serializing message " + message + " to " + clientNames);
		try {
			ByteBuffer bbMessage = bufferSerializationSupport.outputBufferFromObject(message);		
			destination.send(clientNames, bbMessage);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	@Override
//	public void send(String clientName, Object message) {
//		Tracer.info(this, this + " group serializing message " + message + " to " + clientName);
//		try {
//			ByteBuffer bbMessage = bufferSerializationSupport.outputBufferFromObject(message);		
//			destination.send(clientName, bbMessage);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

}
