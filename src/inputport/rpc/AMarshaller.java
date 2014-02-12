package inputport.rpc;

import java.lang.reflect.Method;

public class AMarshaller implements Marshaller<String>{

//	@Override
//	public Call marshallCall(Call aCall) {
//		return new ASerializableCall(aCall);
//	}
//	@Override
//	public Call unMarshallCall(Object aMessage) {
//		return (Call) aMessage;
//		
//	}

	@Override
	public RemoteCall<String> marshallCall(String targetObject, Method method,
			Object[] anArgsList) {
		return new ASerializableCall(targetObject, method, anArgsList);
	}

}
