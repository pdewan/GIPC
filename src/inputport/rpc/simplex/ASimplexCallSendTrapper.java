package inputport.rpc.simplex;

import inputport.ClientInputPort;
import inputport.InputPort;
import inputport.datacomm.AnAbstractSendTrapper;
import inputport.datacomm.NamingSender;
import inputport.rpc.RemoteCall;
import port.trace.CallGenerated;
import util.trace.Tracer;


public class ASimplexCallSendTrapper extends AnAbstractSendTrapper<Object, Object> {

	protected SimplexSentCallCompleter simplexSentCallCompleter;
	protected InputPort inputPort;
	public ASimplexCallSendTrapper(InputPort anInputPort, NamingSender<Object>  aDestination) {
		super(aDestination);	
		inputPort = anInputPort;
//		simplexSentCallCompleter = createSimplexSentCallCompleter();
//		createCallCompleter();
		createSupportingObjects();
	}
	protected void  createSupportingObjects() {
		createSimplexCallCompleter();
	}
	
	protected void  createSimplexCallCompleter() {
		simplexSentCallCompleter = SimplexSentCallCompleterSelector.createSimplexSentCallCompleter();
	}

	@Override
	public void send(String aDestination, Object aMessage) {
		if (aMessage instanceof RemoteCall) {
			CallGenerated.newCase(this, aDestination, "", 
					(RemoteCall) aMessage);
		}
		Tracer.info(this, "Sending call " + aMessage + " to " + aDestination);
		destination.send(aDestination, aMessage);

	}	
	@Override
	public Object returnValue(String aDestination, Object aMessage) {
		Tracer.info(this, "Waiting for return value of call " + aMessage);

		return simplexSentCallCompleter.returnValueOfRemoteMethodCall(null, (RemoteCall) aMessage);
	}
//	protected SimplexSentCallCompleter createSimplexSentCallCompleter() {
//		return SimplexSentCallCompleterSelector.createSimplexSentCallCompleter();
//	}

}
