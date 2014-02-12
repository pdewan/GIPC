package serialization.efficient;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import serialization.ListSerializer;
import util.misc.SeekableByteArrayInputStream;
import util.misc.SeekableByteArrayOutputStream;
import util.trace.Tracer;



public class ASingleStreamSerializer implements ListSerializer   {
	byte[] inputByteArray;
	SeekableByteArrayInputStream seekableByteArrayInputStream ;
	ObjectInputStream objectInputStream;
	SeekableByteArrayOutputStream byteArrayOutputStream = new SeekableByteArrayOutputStream();
	ObjectOutputStream objectOutputStream;

	public ASingleStreamSerializer()  {	
		doInputInitialization();
		doOutputInitialization();		
	}
	void doOutputInitialization () {
		try {
			 objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);

			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void initializeInputState (ByteBuffer inputBuffer) {		
		try {
			inputByteArray = inputBuffer.array();
			int start = inputBuffer.position();
			int end = inputBuffer.limit();
			seekableByteArrayInputStream = new SeekableByteArrayInputStream(inputByteArray, start, end - start);
			objectInputStream = new ObjectInputStream(seekableByteArrayInputStream);		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void doInputInitialization () {		
		try {		
			seekableByteArrayInputStream = new SeekableByteArrayInputStream(SeekableByteArrayOutputStream.getHeaderBytes());
			objectInputStream = new ObjectInputStream(seekableByteArrayInputStream);		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	void incorporateIntoByteArrayInputStream(ByteBuffer inputBuffer) {
		try {
		seekableByteArrayInputStream.setBuffer(inputBuffer.array());
		seekableByteArrayInputStream.setPosition(inputBuffer.position());
		seekableByteArrayInputStream.setCount(inputBuffer.limit());	
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	public synchronized Object  objectFromInputBuffer(ByteBuffer inputBuffer) {

		List<Object> objects = objectsFromInputBuffer(inputBuffer);
		return objects.get(0);
	}

	public synchronized List<Object>  objectsFromInputBuffer(ByteBuffer inputBuffer) {

		incorporateIntoByteArrayInputStream(inputBuffer); 
		List<Object> retVal = new ArrayList();
		while (true) {					 
			try {

			Object readObject = objectInputStream.readObject();
			retVal.add(readObject);
			Tracer.info(this, "Read serializable object: " + readObject);
			} catch (EOFException eof) {					
				break;
			} catch (Exception e) {
				e.printStackTrace();
;
				return retVal;
			}
		}

		return retVal;
	}

	
	public synchronized ByteBuffer outputBufferFromObject(Object object ) {
		Object[] objects = {object};
		return outputBufferFromObjects(objects);

	}
	
	public synchronized ByteBuffer outputBufferFromObjects(Object[] objects ) {
		try {
		byteArrayOutputStream.reset(); //makes sure we do not send header bytes
		objectOutputStream.reset(); // if we do not do this, there is some state information that screws up our stateless sending


		for (int i = 0; i < objects.length; i++) {
			Tracer.info(this, "Serializing object:" + objects[i]);
			objectOutputStream.writeObject(objects[i]);
		}
		objectOutputStream.flush();
		ByteBuffer retVal = ByteBuffer.wrap(byteArrayOutputStream.getBuffer(), 0, byteArrayOutputStream.size());
		Tracer.info(this, "Converted serialized data:" + retVal);
		return retVal;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
