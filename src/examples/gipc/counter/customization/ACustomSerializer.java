package examples.gipc.counter.customization;

import java.nio.ByteBuffer;

import serialization.simple.ASimpleSerializer;

public class ACustomSerializer extends ASimpleSerializer {
	@Override
	public synchronized Object objectFromInputBuffer(ByteBuffer inputBuffer) {
		Object retVal = super.objectFromInputBuffer(inputBuffer);
		System.out.println (inputBuffer + "-->" + retVal);
		return retVal;
	}
	@Override
	public ByteBuffer outputBufferFromObject(Object object) {
		ByteBuffer retVal = super.outputBufferFromObject(object);
		System.out.println(object + "-->" + retVal);
		return retVal;
	}
	
}
