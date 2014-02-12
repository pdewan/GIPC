package inputport.rpc;

public class MarshallerSelector {
	static MarshallerFactory marshallerFactory = new AMarshallerFactory();

	public static MarshallerFactory getMarshallerFactory() {
		return marshallerFactory;
	}

	public static void setMarshallerFactory(MarshallerFactory marshallerFactory) {
		MarshallerSelector.marshallerFactory = marshallerFactory;
	}
	public static Marshaller createMarshaller() {
		return marshallerFactory.createMarshaller();
	}

}
