package oldrpcip;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface ReplyRPC {
	Serializable reply(String name, Method method, Object[] args);
	Serializable reply(Method method, Object[] args);
	Serializable reply(Class type, Method method, Object[] args);
}
