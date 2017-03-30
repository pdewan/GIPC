package inputport.rpc.group;

import java.lang.reflect.Method;
import java.util.Set;

public interface GroupNamingRPC  {	
//	Object[] call(Set<String> clientNames, String objectName, Method method,
//			Object[] args);
//	Object[] call(Set<String> clientNames, Method method,
//			Object[] args);
//	Object[] call(Class type, Set<String> clientNames, Method method,
//			Object[] args);
	
	Object call(Set<String> clientNames, String objectName, Method method,
			Object[] args);
	Object call(Set<String> clientNames, Method method,
			Object[] args);
	Object call(Class type, Set<String> clientNames, Method method,
			Object[] args);
}
