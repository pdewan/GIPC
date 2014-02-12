package oldrpcip;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface UniImplicitRPC {
	Serializable call(String name, Method method, Object[] args);
	Serializable call(Method method, Object[] args);
	Serializable call(Class type, Method method, Object[] args);
}
