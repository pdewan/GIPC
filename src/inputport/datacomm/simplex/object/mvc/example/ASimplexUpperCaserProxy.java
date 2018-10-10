package inputport.datacomm.simplex.object.mvc.example;

import examples.mvc.local.simplex.SimplexUpperCaser;
import inputport.datacomm.ImplicitSender;

public class ASimplexUpperCaserProxy implements SimplexUpperCaser{
	ImplicitSender<Object> sender;
	SimplexUpperCaseClientMarshaller clientMarshaller;
	public ASimplexUpperCaserProxy(ImplicitSender<Object> anInputPort) {
		sender = anInputPort;
		clientMarshaller = new ASimplexClientUpperCaseMarshaller();
	}
	@Override
	public void printUpperCase(String aString) {
		sender.send(clientMarshaller.marshallPrintUpperCase(aString));
	}	

	
}
