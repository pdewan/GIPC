package inputport.rpc;

import java.lang.reflect.Method;

import util.misc.RemoteReflectionUtility;
import util.misc.Transient;
import util.trace.Tracer;




public class ASerializableMethod implements SerializableMethod {
	String className;
	String methodName;
	String[] parameterTypeNames;
	transient Method method;
	static final String PARAMETER_SEPARATOR = ",";
	public ASerializableMethod(Method method) {
		className = method.getDeclaringClass().getName();
		methodName = method.getName();
		Class[] parameterClasses = method.getParameterTypes();
		parameterTypeNames = new String[parameterClasses.length];
		for (int i = 0; i < parameterClasses.length; i ++ ) {
			parameterTypeNames[i] = parameterClasses[i].getName();
		}
	}
	public ASerializableMethod() {
		
	}
	@Transient
	public Method getMethod() {
		try {
		if (method == null) {
			Class methodClass = Class.forName(className);
			Class[] parameterClasses = new Class[parameterTypeNames.length];
			for (int i = 0; i <parameterTypeNames.length; i++) {
				parameterClasses[i] = RemoteReflectionUtility.forName(parameterTypeNames[i]);
			}
			method = methodClass.getMethod(methodName, parameterClasses);			
		}
		return method;
		} catch (Exception e) {
			Tracer.error("Could not derive method from: " + this);
			e.printStackTrace(); 
			return null;
		}
	}
	@Override
	public String toString() {
		return methodName;
//		return toStringMethod();
	}
	// for property based custom serialization
	@Override
	public String getClassName() {
		return className;
	}
	@Override
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String getMethodName() {
		return methodName;
	}
	@Override
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	@Override
	public String[] getParameterTypeNames() {
		return parameterTypeNames;
	}
	@Override
	public void setParameterTypeNames(String[] parameterTypeNames) {
		this.parameterTypeNames = parameterTypeNames;
	}
	 String toStringyParameterTypes() {
		 if (getParameterTypeNames() == null) return "";
		 StringBuilder result = new StringBuilder();
		 for (int i = 0; i < getParameterTypeNames().length; i++) {
			 if (i > 0) {
				 result.append(PARAMETER_SEPARATOR);
			 }
			 result.append(getParameterTypeNames()[i]);
		 }
		 return result.toString();
	 }
	 @Override
	public String toHeader() {
		 StringBuilder result = new StringBuilder();
		 result.append(methodName);
		 result.append("(");
		 result.append(toStringyParameterTypes());		
		 result.append(")");
		 return result.toString();

	 }
	 public void initSerializedObject() {
			
		}

}
