package inputport.rpc;

import java.lang.reflect.Method;

public interface ImplicitRPC {
	Object call(String name, Method method, Object[] args);
	Object call(Method method, Object[] args);
	Object call(Class type, Method method, Object[] args);
}
