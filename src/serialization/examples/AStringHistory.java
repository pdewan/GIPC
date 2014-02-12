package serialization.examples;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class AStringHistory implements StringHistory/* , Externalizable */{
	public final int MAX_SIZE = 50;
	transient String[] contents = new String[MAX_SIZE];
	transient int size = 0;

	public int size() {
		return size;
	}

	public String get(int index) {
		return contents[index];
	}

	boolean isFull() {
		return size == MAX_SIZE;
	}

	public void add(String element) {
		if (isFull())
			System.out.println("Adding item to a full history");
		else {
			contents[size] = element;
			size++;
		}
	}

	private synchronized void writeObject(java.io.ObjectOutputStream stream)
			throws java.io.IOException {
		// stream.defaultWriteObject( );
		stream.writeInt(size);
		for (int i = 0; i < size; i++)
			stream.writeObject(contents[i]);
	}

	private void readObject(java.io.ObjectInputStream stream)
			throws java.io.IOException {
		try {
			contents = new String[MAX_SIZE];
			// stream.defaultReadObject( );
			size = stream.readInt();
			for (int i = 0; i < size; i++)
				contents[i] = (String) stream.readObject();

		} catch (ClassNotFoundException ce) {
			ce.printStackTrace();
		}
	}

	// @Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(size);
		for (int i = 0; i < size; i++)
			out.writeObject(contents[i]);

	}

	// @Override
	public void readExternal(ObjectInput in) throws IOException,
			ClassNotFoundException {
		// contents = new String[MAX_SIZE];
		size = in.readInt();
		for (int i = 0; i < size; i++)
			contents[i] = (String) in.readObject();

	}

	public String toString() {
		String retVal = super.toString() + "(";
		for (int i = 0; i < size(); i++) {
			if (i != 0)
				retVal += ",";
			retVal += get(i);
		}
		return retVal + ")";
	}

}
