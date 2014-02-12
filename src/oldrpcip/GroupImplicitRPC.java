package oldrpcip;

import java.lang.reflect.Method;

public interface GroupImplicitRPC  {
	Object[] callAll(String objectName, Method method, Object[] args);
	Object[] callOthers(String objectName, Method method, Object[] args);
	
	Object[] callAll(Method method, Object[] args);
	Object[] callOthers(Method method, Object[] args);
	
	Object[] callAll(Class type, Method method, Object[] args);
	Object[] callOthers(Class type, Method method, Object[] args);
}
