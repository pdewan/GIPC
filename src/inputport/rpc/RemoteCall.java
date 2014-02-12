package inputport.rpc;

import java.io.Serializable;
import java.lang.reflect.Method;

public interface RemoteCall<ObjectIdType> extends Serializable {
	Method getMethod();
	Object[] getArgs();
	public void setArgs(Object[] args) ;
	// setter here because other wise it is not recognized as property
	ObjectIdType getTargetObject();
	void setTargetObject(ObjectIdType newVal);
}