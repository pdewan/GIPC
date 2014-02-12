package inputport.datacomm.simplex.buffer.mvc.example;

import inputport.datacomm.simplex.object.mvc.example.PrintUpperCaseCall;

import java.io.NotSerializableException;
import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;


public interface PrintUpperCaseCallSerializer {	
    PrintUpperCaseCall objectFromInputBuffer(ByteBuffer inputBuffer) throws StreamCorruptedException;
	ByteBuffer outputBufferFromObject(PrintUpperCaseCall object);

}
