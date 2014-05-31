package inputport.datacomm.simplex.buffer.example;
import inputport.InputPort;
import inputport.datacomm.simplex.buffer.AnEchoingBufferReceiveListener;

public class AnUpperCaseBufferReceiveListener extends AnEchoingBufferReceiveListener  {
	public AnUpperCaseBufferReceiveListener (InputPort anInputPort) {
		super(anInputPort);
	}
	@Override
	protected  String computeResponse (String anInput) {
		return  anInput.toUpperCase();
	}
	
}
