package extraip;

import inputport.rpc.ASerializableCall;

import java.lang.reflect.Method;


public class ASequencedSerializableCall extends ASerializableCall implements SequencedSerializableCall{
	int id;
	public ASequencedSerializableCall(String theName, Method theMethod, Object[] theArgs, int anId) {
		super(theName, theMethod, theArgs);
		id = anId;
	}	
	public int getID() {
		return id;
	}
	@Override
	public String toString() {
		return  super.toString() + "id: " + id;
	}	
	public void setID(int anId) {
		id = anId;
	}
}
