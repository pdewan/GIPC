package inputport.datacomm.simplex.buffer.mvc.example;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;

import inputport.datacomm.simplex.object.mvc.example.APrintUpperCaseCall;
import inputport.datacomm.simplex.object.mvc.example.PrintUpperCaseCall;
import port.common.DistMisc;
import serialization.Serializer;

public class APrintUpperCaseCallSerializer  implements Serializer {
	public static final String PRINT_UPPER_CASE = "Print Upper Case:";
	@Override
	public Object objectFromInputBuffer(ByteBuffer aByteBuffer)
			throws StreamCorruptedException {
	
		String inputString = DistMisc.toString(aByteBuffer);
		if (!inputString.startsWith(PRINT_UPPER_CASE))
			throw new StreamCorruptedException("Expecting serialized byte buffer to start with:" + PRINT_UPPER_CASE);
			
		return new APrintUpperCaseCall (inputString.substring(PRINT_UPPER_CASE.length()));
	}

	@Override
	public ByteBuffer outputBufferFromObject(Object object) throws NotSerializableException	 {
		if (!(object instanceof PrintUpperCaseCall))
			throw new NotSerializableException("Can only serialize instances of PrintUpperCaseCall");
		String outputString = PRINT_UPPER_CASE + ((PrintUpperCaseCall)object).getString();		
		return ByteBuffer.wrap(outputString.getBytes());
		
	}

}
