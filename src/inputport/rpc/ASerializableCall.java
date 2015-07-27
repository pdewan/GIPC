package inputport.rpc;


import java.lang.reflect.Method;

import util.annotations.DisplayToString;
import util.annotations.Visible;
import util.misc.Transient;

@DisplayToString(true)
public class ASerializableCall extends ARemoteCall implements SerializableCall{
//	String targetObject;
	
	SerializableMethod serializedMethod;
//	Object[] args;
	public ASerializableCall(String theName, Method theMethod, Object[] theArgs) {
		super(theName, theMethod, theArgs);
//		targetObject = theName;
		serializedMethod = new ASerializableMethod(theMethod);
//		args = theArgs;
	}
	public ASerializableCall() {		
	}
	public ASerializableCall(RemoteCall<String> aCall) {
		this(aCall.getTargetObject(), aCall.getMethod(), aCall.getArgs());
	}
	
	public SerializableMethod getSerializableMethod() {
		return serializedMethod;
	}	
	public void setSerializableMethod(SerializableMethod newVal) {
		serializedMethod = newVal;
	}
//	public String getTargetObject() {
//		return targetObject;
//	}
//	public void setTargetObject(String targetObject) {
//		this.targetObject = targetObject;
//	}
//	// setters for property based serialization
//	public Object[] getArgs() {
//		return args;
//	}
	
		
	public void setSerializedMethod(SerializableMethod method) {
		this.serializedMethod = method;
	}	
//	public void setArgs(Object[] args) {
//		this.args = args;
//	}
	@Visible(false)
	@Transient
	public Method getMethod() {
		return serializedMethod.getMethod();
	}	
	public static String toStringArray(Object[] anArray) {
		if (anArray == null) return "null";
		String retVal = "[";
		for (int i = 0; i < anArray.length; i++) {
			if (i != 0)
				retVal += ",";
			Object element = anArray[i];
			if (element instanceof Number || element instanceof String)
				retVal += anArray[i];
			else
				retVal += "_";
		}
		retVal += "]";
		return retVal;
	}
	public String toString() {
		return  getClass().getSimpleName() + "(" +   targetObject + "." + serializedMethod + toStringArray(args) + ")";
	}
	
	public void initSerializedObject() {
		
	}
	
}
