package serialization.examples;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class AnObjectHistory<ElementType> implements ObjectHistory<ElementType>/* , Externalizable */{
	transient public final int MAX_SIZE = 50;
	transient Object[] contents = new Object[MAX_SIZE];
	int size = 0;
	public int size() {return size;	}

	public ElementType get(int index) {
		return (ElementType) contents[index];
	}
	boolean isFull() {
		return size == MAX_SIZE;
	}
	public void add(ElementType element) {
		if (isFull())
			System.out.println("Adding item to a full history");
		else {
			contents[size] = element;
			size++;
		}
	}
	private void writeObject(java.io.ObjectOutputStream stream) {
		try {
	    stream.defaultWriteObject( );
		for (int i = 0; i < size; i++)
			stream.writeObject(contents[i]);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	private void readObject(java.io.ObjectInputStream stream) {
		try {
		    stream.defaultReadObject();
			contents = new Object[MAX_SIZE];
			for (int i = 0; i < size; i++)
				contents[i] =  stream.readObject();
		} catch (Exception ce) {
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
		contents = new Object[MAX_SIZE];
		size = in.readInt();
		for (int i = 0; i < size; i++)
			contents[i] = in.readObject();

	}

//	public String toString() {
//		String retVal = super.toString() + "(";
//		for (int i = 0; i < size(); i++) {
//			if (i != 0)
//				retVal += ",";
//			retVal += get(i);
//		}
//		return retVal + ")";
//	}

}
