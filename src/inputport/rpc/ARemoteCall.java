package inputport.rpc;

import java.lang.reflect.Method;

public class ARemoteCall implements RemoteCall<String> {
	transient Method method;
	String targetObject;
	Object[] args;
	public ARemoteCall() {
		
	}
	public ARemoteCall(String aTargetObject, Method aMethod,  Object[] anArgsList) {
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
	
	public String getTargetObject() {
		return targetObject;
	}
	public void setTargetObject(String newVal) {
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

}
