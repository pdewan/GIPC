package inputport.rpc;

import java.lang.reflect.Method;

// This is stateless. It is assumed some stateful object
// has replaced actual ags and return vals with remote references
public interface Marshaller<ObjectIdType> {
	RemoteCall<ObjectIdType> marshallCall(ObjectIdType aTargetObject, Method aMethod, Object[] anArgsList);
//	Call unMarshallCall(Object aMessage);

}
