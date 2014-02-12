package oldrpcip;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface UniNamingRPC {
	Serializable call(String clientName, String objectName, Method method, Object[] args);
	Serializable call(String clientName, Class interfaceName, Method method, Object[] args);
	Serializable call(String clientName,  Method method, Object[] args);


}
