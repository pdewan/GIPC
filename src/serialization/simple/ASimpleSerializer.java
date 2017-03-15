package serialization.simple;

import java.io.ByteArrayInputStream;
import java.io.EOFException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import serialization.ListSerializer;
import util.misc.SeekableByteArrayOutputStream;
import util.trace.Tracer;


public class ASimpleSerializer implements ListSerializer {

	public synchronized Object objectFromInputBuffer(ByteBuffer inputBuffer) {
		List<Object> objects = objectsFromInputBuffer(inputBuffer);
		if (objects == null) {
			return null;
		}
		return objects.get(0);
	}

	public List<Object> objectsFromInputBuffer(ByteBuffer inputBuffer) {
		try {
//			ObjectInputStream objectInputStream = new ObjectInputStream(
//					new ByteArrayInputStream(inputBuffer.array(), inputBuffer
//							.position(), inputBuffer.limit()
//							- inputBuffer.position()));
			Tracer.info(this, "Buffer with serialized objects:" + inputBuffer);
			ObjectInputStream objectInputStream = new ObjectInputStream(
					new ByteArrayInputStream(inputBuffer.array(), inputBuffer
							.position(), inputBuffer.remaining()));

			List<Object> retVal = new ArrayList();
			while (true) {
				try {

					Object readObject = objectInputStream.readObject();
					retVal.add(readObject);
					Tracer.info(this, "Read serializable object:" + readObject);
				} catch (EOFException eof) {
					break;
				}
//				} catch (Exception e) {
//					e.printStackTrace();
//					retVal.add(inputBuffer); // let the receiver handle it
//				}
			}

			return retVal;
		} catch (Exception e) {
//			e.printStackTrace();
			return null;
		}
	}

	public ByteBuffer outputBufferFromObject(Object object) {
		Object[] objects = { object };
		return outputBufferFromObjects(objects);
	}

	public ByteBuffer outputBufferFromObjects(Object[] objects) {
		try {
			SeekableByteArrayOutputStream byteArrayOutputStream = new SeekableByteArrayOutputStream();
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(
					byteArrayOutputStream);
			for (int i = 0; i < objects.length; i++) {
				Tracer.info(this, "Serializing object " + objects[i]);
				objectOutputStream.writeObject(objects[i]);
			}
			objectOutputStream.flush();
			ByteBuffer retVal = ByteBuffer.wrap(byteArrayOutputStream
					.getBuffer(), 0, byteArrayOutputStream.size());
//			Tracer.info(this, "Converted serialized data " + retVal);
			Tracer.info(this, "Buffer with serialized objects:" + retVal);

			return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
