package inputport.rpc;

import java.lang.reflect.Method;

public interface LocalCall<ObjectIdType>  {
	Method getMethod();
	Object[] getArgs();
	public void setArgs(Object[] args) ;
	// setter here because other wise it is not recognized as property
	Object getTargetObject();
	void setTargetObject(Object newVal);
}