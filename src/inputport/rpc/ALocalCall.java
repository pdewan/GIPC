package inputport.rpc;

import java.lang.reflect.Method;

public class ALocalCall implements LocalCall {
    Method method;
	Object targetObject;
	Object[] args;
	public ALocalCall() {
		
	}
	public ALocalCall(Object aTargetObject, Method aMethod,  Object[] anArgsList) {
		method = aMethod;
		targetObject = aTargetObject;
		args = anArgsList;
	}
	 
	public Method getMethod() {
		return method;
	}
	
//	public void setMethod(Method method) {
//		this.method = method;
//	}
	
	public Object getTargetObject() {
		return targetObject;
	}
	public void setTargetObject(Object newVal) {
		targetObject = newVal;
	}
	
//	public void setTargetObject(String targetObject) {
//		this.targetObject = targetObject;
//	}
	
	public Object[] getArgs() {
		return args;
	}
	
	public void setArgs(Object[] args) {
		this.args = args;
	}
	public  boolean equalArgs( Object[] args2 ) {
		if (args == null) return args2 == null;
		if (args.length != args2.length) 
			return false;
		for (int i = 0; i < args2.length; i++) {
			if (args[i] != args2[i]) return false;
		}
		return true;
	}
	public boolean equals (Object anOtherObject) {
		if (!(anOtherObject instanceof LocalCall))
			return false;
		LocalCall anOtherLocalCall = (LocalCall) anOtherObject;
		return targetObject == anOtherLocalCall.getTargetObject() &&
				method == anOtherLocalCall.getMethod() &&
				equalArgs(anOtherLocalCall.getArgs());
	}

}
