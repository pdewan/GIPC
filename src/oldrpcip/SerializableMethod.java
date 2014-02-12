package oldrpcip;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface SerializableMethod extends Serializable {
	Method getMethod();
}
