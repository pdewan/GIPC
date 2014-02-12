package inputport.rpc.duplex;

import java.lang.reflect.Method;

public interface ReplyRPC {
	Object reply(String name, Method method, Object[] args);
	Object reply(Method method, Object[] args);
	Object reply(Class type, Method method, Object[] args);
}
