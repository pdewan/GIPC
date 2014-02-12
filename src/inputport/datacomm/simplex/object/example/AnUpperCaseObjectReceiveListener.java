package inputport.datacomm.simplex.object.example;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;

public class AnUpperCaseObjectReceiveListener extends AnEchoingObjectReceiveListener  {

	public AnUpperCaseObjectReceiveListener(InputPort anInputPort) {
		super(anInputPort);
	}

	protected String computeResponse(String anInput) {
		return anInput.toUpperCase();
	}

}
