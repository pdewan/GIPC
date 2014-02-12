package inputport.datacomm.simplex.buffer.example;
import inputport.InputPort;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.simplex.buffer.example.AnEchoingBufferReceiveListener;

import java.nio.ByteBuffer;

import port.common.DistMisc;

public class AnUpperCaseBufferReceiveListener extends AnEchoingBufferReceiveListener  {
	public AnUpperCaseBufferReceiveListener (InputPort anInputPort) {
		super(anInputPort);
	}
	@Override
	protected  String computeResponse (String anInput) {
		return  anInput.toUpperCase();
	}
	
}
