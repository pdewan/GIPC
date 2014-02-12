package inputport.datacomm.simplex.object.mvc.example;

import inputport.datacomm.ImplicitSender;
import inputport.datacomm.NamingSender;
import examples.mvc.local.simplex.SimplexUpperCaser;

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
