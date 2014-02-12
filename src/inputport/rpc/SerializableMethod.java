package inputport.rpc;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface SerializableMethod extends Serializable {
	Method getMethod();
	void setParameterTypeNames(String[] parameterTypeNames);
	String[] getParameterTypeNames();
	void setMethodName(String methodName);
	String getMethodName();
	void setClassName(String className);
	String getClassName();
}
