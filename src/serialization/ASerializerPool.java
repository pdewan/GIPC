package serialization;

import inputport.datacomm.simplex.buffer.SendRegistrar;
import inputport.datacomm.simplex.buffer.nio.AScatterGatherSelectionManager;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import port.trace.objects.BufferDeserializationInitiated;
import port.trace.objects.ObjectSerializationInitiated;
import port.trace.objects.SerializerPoolCreated;
import port.trace.objects.SerializerReturnedToPool;
import port.trace.objects.SerializerTakenFromPool;
import util.misc.HashIdentityMap;
import util.misc.IdentityMap;
import util.trace.Tracer;




public class ASerializerPool implements SerializerPool {
	BlockingQueue<Serializer> outputSupportBoundedBuffer;
	Serializer inputSupport;
	IdentityMap<byte[], Serializer> bytesToSerializer;
	boolean initializingBufferPool;
	SendRegistrar sendNotifier;
	public ASerializerPool(SendRegistrar theSendNotifier) {
		if (theSendNotifier != null) {
			theSendNotifier.addSendListener(this);
			sendNotifier = theSendNotifier;
		}
		outputSupportBoundedBuffer =  new ArrayBlockingQueue(AScatterGatherSelectionManager.getMaxOutstandingWrites());
		SerializerPoolCreated.newCase(this, outputSupportBoundedBuffer, outputSupportBoundedBuffer.size());
		inputSupport = SerializerSelector.createSerializer();
		Tracer.info(this, "Initializing serializer pool");
		initializingBufferPool = true;
		for (int i=0; i< AScatterGatherSelectionManager.getMaxOutstandingWrites(); i++) {
			Serializer bufferSerializationSupport = SerializerSelector.createSerializer();
			putOutputBufferSerializationSupport(bufferSerializationSupport);

		}
		bytesToSerializer = new HashIdentityMap();
		initializingBufferPool = false;

	}

	@Override
	public Object objectFromInputBuffer(
			ByteBuffer inputBuffer) throws StreamCorruptedException  {
		Tracer.info(this, "InputBuffer with Serialized Object:" + inputBuffer);
		BufferDeserializationInitiated.newCase(this, "?", inputBuffer, inputSupport);
		Object retVal = inputSupport.objectFromInputBuffer(inputBuffer);
		Tracer.info(this, "Deserialized Object:" + retVal);

		return retVal;
//		return inputSupport.objectFromInputBuffer(inputBuffer);
	}
	Serializer takeOutputBufferSerializationSupport() {
		try {
			if (outputSupportBoundedBuffer.isEmpty()) {
				Tracer.error("Blocking serialing thread, will cause deadlock if this is selection manager thread");
			}
			Tracer.info(this, "consuming output buffer ");
			return outputSupportBoundedBuffer.take();
		} catch (InterruptedException e) {
			// need to think about this
			Thread.currentThread().stop();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	void putOutputBufferSerializationSupport(Serializer bufferSerializationSupport) {
		try {
//			Tracer.info(this, "Starting to put serializer:" + bufferSerializationSupport);
			// does this check == or equals, I suspect equals
			if (outputSupportBoundedBuffer.contains(bufferSerializationSupport)) // this can happen if the same bytes are sent twice by sendOthers
				return; 

//			if (outputSupportBoundedBuffer.size() ==  AScatterGatherSelectionManager.MAX_HEADER_BUFFERS)
//				Tracer.error("Overflow of output buffers and if sending thread is selection thread, deadlock will occur");
			
			outputSupportBoundedBuffer.put(bufferSerializationSupport); // guaranteed to not block
			SerializerReturnedToPool.newCase(this, outputSupportBoundedBuffer, bufferSerializationSupport);
			if (!initializingBufferPool)
			Tracer.info(this, "Producing output buffer");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	@Override
	public ByteBuffer outputBufferFromObject(Object object) throws NotSerializableException {
		Tracer.info(this, "Object to be serialized:" + object);
		Serializer bufferSerializationSupport = takeOutputBufferSerializationSupport();	
		SerializerTakenFromPool.newCase(this, outputSupportBoundedBuffer, bufferSerializationSupport);
//		ObjectSerializationInitiated.newCase(this, "?", object, bufferSerializationSupport);
		ByteBuffer retVal = bufferSerializationSupport.outputBufferFromObject(object);
		bytesToSerializer.put(retVal.array(), bufferSerializationSupport);
		Tracer.info(this, "OutBuffer:" + object);
		return retVal;
	}
	@Override
	public void messageSent(String aRemoteEnd, ByteBuffer message, int sendId) {
		Serializer serializer = bytesToSerializer.get(message.array());
		if (serializer != null) {
			Tracer.info(this, "Returning serializer to  pool:" + serializer);
			putOutputBufferSerializationSupport(serializer);
		}
		
	}

}
