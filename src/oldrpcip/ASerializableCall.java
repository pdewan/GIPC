package oldrpcip;

import java.lang.reflect.Method;

public class ASerializableCall implements SerializableCall{
	String name;
	SerializableMethod method;
	Object[] args;
	public ASerializableCall(String theName, Method theMethod, Object[] theArgs) {
		name = theName;
		method = new ASerializableMethod(theMethod);
		args = theArgs;
	}
	public SerializableMethod getSerializableMethod() {
		return method;
	}
	public Object[] getArgs() {
		return args;
	}
	public String getName() {
		return name;
	}
}
