package inputport.datacomm.simplex.object.mvc.example;

import examples.mvc.local.simplex.SimplexUpperCaser;
import inputport.datacomm.ReceiveListener;
import util.trace.Tracer;

public class ASimplexObjectServerUpperCaseReceiveTrapper implements ReceiveListener<Object>, Runnable {
//	protected InputPort inputPort;
	SimplexUpperCaser simplexUpperCaser;

	public ASimplexObjectServerUpperCaseReceiveTrapper(SimplexUpperCaser aSimplexUpperCaser) {
		simplexUpperCaser = aSimplexUpperCaser;
//		simplexUpperCaser = new ASimplexUpperCaserProxy(anInputPort);
	}
	@Override
	public void messageReceived(String aRemoteEnd, Object aMessage) {
		if (!(aMessage instanceof PrintUpperCaseCall)) {
			Tracer.error("Expecting an instance of " + PrintUpperCaseCall.class + " rather than:" + aMessage.getClass());
			return;
		}
		printUpperCase((PrintUpperCaseCall) aMessage);
			
	}
	
	protected void printUpperCase(PrintUpperCaseCall printUpperCaseCall) {		
		simplexUpperCaser.printUpperCase(printUpperCaseCall.getString());
		
	}
	
	
	@Override
	public void run() {
		
		
	}
	
	


}
