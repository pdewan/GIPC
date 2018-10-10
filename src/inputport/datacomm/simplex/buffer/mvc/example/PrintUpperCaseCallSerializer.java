package inputport.datacomm.simplex.buffer.mvc.example;

import java.io.StreamCorruptedException;
import java.nio.ByteBuffer;

import inputport.datacomm.simplex.object.mvc.example.PrintUpperCaseCall;


public interface PrintUpperCaseCallSerializer {	
    PrintUpperCaseCall objectFromInputBuffer(ByteBuffer inputBuffer) throws StreamCorruptedException;
	ByteBuffer outputBufferFromObject(PrintUpperCaseCall object);

}
