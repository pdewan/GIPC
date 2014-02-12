package oldrpcip;

import java.io.Serializable;

public interface SerializableCall extends Serializable {
	String getName();
	SerializableMethod getSerializableMethod();
	Object[] getArgs();
}
