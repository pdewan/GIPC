package inputport.rpc;


import java.lang.reflect.Method;


public class ASerializableCallCreator implements CallToObjectConverter<SerializableCall> {

	@Override
	public SerializableCall callToObject(String theName, Method theMethod,
			Object[] theArgs) {
		return new ASerializableCall(theName, theMethod, theArgs);
	}	

}
