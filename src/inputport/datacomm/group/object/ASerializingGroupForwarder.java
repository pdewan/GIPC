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





public class ASerializingGroupForwarder extends AnAbstractGroupSendTrapper<Object, ByteBuffer> {
//	GroupNamingSender<ByteBuffer> destination;
	protected Serializer bufferSerializationSupport;
	InputPort inputPort;
	public ASerializingGroupForwarder(InputPort anInputPort, GroupNamingSender<ByteBuffer>  aDestination) {
		super (aDestination);
		inputPort = anInputPort;
		bufferSerializationSupport = SerializerPoolSelector.createSerializerPool((SendRegistrar) inputPort);
	}
	@Override
	public void send(Set<String> clientNames, Object message) {
		Tracer.info(this, this + " group serializing message " + message + " to " + clientNames);
		try {
			ByteBuffer bbMessage = bufferSerializationSupport.outputBufferFromObject(message);		
			destination.send(clientNames, bbMessage);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public void send(String clientName, Object message) {
		Tracer.info(this, this + "  serializing message " + message + " to " + clientName);
		try {
			ByteBuffer bbMessage = bufferSerializationSupport.outputBufferFromObject(message);		
			destination.send(clientName, bbMessage);
		} catch (RuntimeException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
//	@Override
//	public void send(String remoteEnd, Object message) {
//		Tracer.info(this, this + " group serializing message " + message + " to " + remoteEnd);
//		try {
//			ByteBuffer bbMessage = bufferSerializationSupport.outputBufferFromObject(message);		
//			destination.send(remoteEnd, bbMessage);
//		} catch (RuntimeException e) {
//			throw e;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//	}

}
