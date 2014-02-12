package extraip;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import util.misc.SeekableByteArrayInputStream;
import util.misc.SeekableByteArrayOutputStream;




public class ASingleBufferSerializationSupport implements SingleBufferSerializationSupport   {
	public static int INPUT_BUFFER_SIZE = 8192;
	private byte[] inputByteArray = new byte[INPUT_BUFFER_SIZE];
	SeekableByteArrayInputStream byteArrayInputStream = new SeekableByteArrayInputStream(inputByteArray, 0, 0);
	ByteBuffer inputBuffer = ByteBuffer.wrap(inputByteArray);
	ObjectInputStream objectInputStream;
	SeekableByteArrayOutputStream byteArrayOutputStream = new SeekableByteArrayOutputStream();
	ObjectOutputStream objectOutputStream;
	ByteBuffer outputBuffer;
	boolean firstOutput = true;
	boolean firstInput = true;
	private byte[] headerBytes = SeekableByteArrayOutputStream.getHeaderBytes();
	void maybeResetOutputBuffer() {
		if (outputBuffer == null || outputBuffer.array() != byteArrayOutputStream.getBuffer()) 
			outputBuffer = ByteBuffer.wrap(byteArrayOutputStream.getBuffer(), 0, byteArrayOutputStream.getBuffer().length);
		//System.out.println(outputBuffer.array() == byteArrayOutputStream.getBuffer());
	}
	public ASingleBufferSerializationSupport()  {	
		doInputInitialization();
		//inputBuffer.clear();
		doOutputInitialization();		
	}
	void doOutputInitialization () {
		try {
			 objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
			 outputBuffer = ByteBuffer.wrap(byteArrayOutputStream.getBuffer(), 0, byteArrayOutputStream.getBuffer().length);
			 //maybeResetOutputBuffer();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void doInputInitialization() {
		inputBuffer.clear();
		//maybeCreateObjectInputStream();	
	}
	void createObjectInputStream () {		
		try {
			objectInputStream = new ObjectInputStream(byteArrayInputStream);		
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	void maybeCreateObjectInputStream () {
		if (objectInputStream != null)
			return;	
		createObjectInputStream();
//		try {
//			objectInputStream = new ObjectInputStream(byteArrayInputStream);		
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
	
	public ByteBuffer getInputBuffer() {
		return inputBuffer;
	}
	public void setInputBuffer(ByteBuffer newVal) {
		if (newVal == inputBuffer) return;
		inputBuffer = newVal;
		byteArrayInputStream = new SeekableByteArrayInputStream(newVal.array(), 0, 0);
		objectInputStream = null;
		//createObjectInputStream();
	}
	
	public ByteBuffer getOutputBuffer() {
		return outputBuffer;
	}
//	public void setOutputBuffer(ByteBuffer newVal) {
//		outputBuffer = newVal;
//	}
	boolean containsHeader() {
		if (byteArrayInputStream.getCount() - byteArrayInputStream.getPosition() < headerBytes.length)
			return false;
		for (int i=0; i < headerBytes.length; i++) {
			if (headerBytes[i] != inputBuffer.get(i + byteArrayInputStream.getPosition()))
				return false;
		}
		return true;		
	}
	public synchronized List<Serializable>  getSerializablesFromInputBuffer() {
		return getSerializablesFromInputBuffer(inputBuffer.limit());
	}

	/* (non-Javadoc)
	 * @see typedip.BufferSerializationSupport#getSerializablesFromInputBuffer()
	 */
	public synchronized List<Serializable>  getSerializablesFromInputBuffer(int messageLength) {
		int posBeforeRead = inputBuffer.position();
		//byteArrayInputStream.setCount(inputBuffer.position());
		byteArrayInputStream.setCount(posBeforeRead);
		maybeCreateObjectInputStream();
		List<Serializable> retVal = new ArrayList();
		int bytesRead = 0;
		while (true) {
			if ((inputBuffer.position() - posBeforeRead) > messageLength )
				break;			 
			try {
				
				//System.out.println("Contains header bytes:" + containsHeader());
				if (firstInput) {
					firstInput = false;
				} else if (containsHeader()) {
						byteArrayInputStream.advancePosition(headerBytes.length);
				}
			Serializable readObject = (Serializable) objectInputStream.readObject();
			retVal.add(readObject);
			System.out.println(readObject);
			} catch (EOFException eof) {					
				break;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		inputBuffer.clear();
		byteArrayInputStream.setCount(0);
		byteArrayInputStream.setPosition(0);
		return retVal;
	}
	public List<Serializable> serializablesFromInputBuffer(ByteBuffer theInputBuffer)  {
		setInputBuffer(theInputBuffer);
		return getSerializablesFromInputBuffer();
	}
	
	
	public synchronized void putSerializableInOutputBuffer(Serializable object ) {
		Serializable[] objects = {object};
		putSerializablesInOutputBuffer(objects);
//		try {
//		if (firstOutput) {
//			firstOutput = false;
//		} else {
//			byteArrayOutputStream.reset();
//			outputBuffer.clear();
//		}		
//		objectOutputStream.writeObject(object);
//		objectOutputStream.flush();
//		//outputBuffer.position(byteArrayOutputStream.getCount());
//		maybeResetOutputBuffer();
//		outputBuffer.limit(byteArrayOutputStream.getCount());
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
	}
	/* (non-Javadoc)
	 * @see typedip.BufferSerializationSupport#putSerializablesInOutputBuffer(java.io.Serializable[])
	 */
	public void putSerializablesInOutputBuffer(Serializable[] objects ) {
		try {
		if (firstOutput) {
			firstOutput = false;
		} else {
			byteArrayOutputStream.reset();
			outputBuffer.clear();
		}
		for (int i = 0; i < objects.length; i++) {
			objectOutputStream.writeObject(objects[i]);
		}
		objectOutputStream.flush();
		//outputBuffer.position(byteArrayOutputStream.getCount());
		maybeResetOutputBuffer();
		outputBuffer.limit(byteArrayOutputStream.getCount());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
