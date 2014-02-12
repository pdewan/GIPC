package inputport.rpc;

public class AMarshallerFactory implements MarshallerFactory {

	@Override
	public Marshaller createMarshaller() {
		return new AMarshaller();
	}
	

}
