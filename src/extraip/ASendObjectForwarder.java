package extraip;

import inputport.datacomm.NamingSender;
import util.trace.Tracer;


public class ASendObjectForwarder  implements NamingSender<Object>{
	NamingSender<Object> destination;
	public ASendObjectForwarder(NamingSender<Object>  aDestination) {
		destination = aDestination;
	}

	@Override
	public void send(String remoteName, Object message) {
		Tracer.info(this, "Forwarding sent object:" + message + " to:" + "remoteName");
		destination.send(remoteName, message);
	}

}
