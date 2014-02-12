package inputport.rpc;


import java.lang.reflect.Method;

public class CallToObjectCreatorSelector {
	static CallToObjectConverter converter = new ASerializableCallCreator();

	public static CallToObjectConverter getConverter() {
		return converter;
	}

	public static void setConverter(CallToObjectConverter converter) {
		CallToObjectCreatorSelector.converter = converter;
	}
	public static Object callToObject(String theName, Method theMethod, Object[] theArgs){
		return converter.callToObject(theName, theMethod, theArgs);
	}
}
