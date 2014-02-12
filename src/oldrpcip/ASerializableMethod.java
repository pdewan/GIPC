package oldrpcip;

import java.lang.reflect.Method;


public class ASerializableMethod implements SerializableMethod {
	String className;
	String methodName;
	String[] parameterTypeNames;
	transient Method method;
	public ASerializableMethod(Method method) {
		className = method.getDeclaringClass().getName();
		methodName = method.getName();
		Class[] parameterClasses = method.getParameterTypes();
		parameterTypeNames = new String[parameterClasses.length];
		for (int i = 0; i < parameterClasses.length; i ++ ) {
			parameterTypeNames[i] = parameterClasses[i].getName();
		}
	}
	
	public Method getMethod() {
		try {
		if (method == null) {
			Class methodClass = Class.forName(className);
			Class[] parameterClasses = new Class[parameterTypeNames.length];
			for (int i = 0; i <parameterTypeNames.length; i++) {
				parameterClasses[i] = ReflectionUtility.forName(parameterTypeNames[i]);
			}
			method = methodClass.getMethod(methodName, parameterClasses);			
		}
		return method;
		} catch (Exception e) {
			return null;
		}
	}

}
