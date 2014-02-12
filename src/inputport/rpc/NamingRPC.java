package inputport.rpc;

import java.lang.reflect.Method;

public interface NamingRPC {
	Object call(String remoteEnd, String objectName, Method method, Object[] args);
	Object call(String remoteEnd, Class type, Method method, Object[] args);
	Object call(String remoteEnd,  Method method, Object[] args);

}
