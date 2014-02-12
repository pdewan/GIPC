package inputport.rpc.duplex.echoingadder.example;

import util.trace.Tracer;

public class ASimpleAdder implements SimpleAdder {

	@Override
	public int add(SimpleEchoer echoer, int arg1, int arg2) {
		Tracer.info(this, "adder called");
		echoer.echo("Server Adding (" + arg1 + "," + arg2 + ")");
		int retVal = arg1 + arg2;
		return retVal;
	}

}
