package extraip;

import java.lang.reflect.Method;

import inputport.rpc.ASerializableCall;
import inputport.rpc.CallToObjectConverter;
import inputport.rpc.SerializableCall;
import inputport.rpc.duplex.LocalRemoteReferenceTranslator;


public class ADuplexSerializableCallCreator implements CallToObjectConverter<SerializableCall> {
	LocalRemoteReferenceTranslator translator;
	
	@Override
	public SerializableCall callToObject(String theName, Method theMethod,
			Object[] theArgs) {
		return new ASerializableCall(theName, theMethod, theArgs);
	}
	
	
	

}
