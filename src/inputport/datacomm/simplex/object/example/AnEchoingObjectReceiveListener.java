package inputport.datacomm.simplex.object.example;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;

public class AnEchoingObjectReceiveListener implements ReceiveListener<Object> {
	protected InputPort inputPort;

	public AnEchoingObjectReceiveListener(InputPort anInputPort) {
		inputPort = anInputPort;
	}

	protected String computeResponse(String anInput) {
		return anInput;
	}

	@Override
	public void messageReceived(String aRemoteEnd, Object aMessage) {
		System.out.println(inputPort.getLocalName() + "<--" + aRemoteEnd + ":"
				+ computeResponse((String) aMessage));
	}
}
