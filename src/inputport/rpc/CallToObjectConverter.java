package inputport.rpc;


import java.lang.reflect.Method;

public interface CallToObjectConverter<ObjectType> {
	ObjectType callToObject(String theName, Method theMethod, Object[] theArgs);
	
}
