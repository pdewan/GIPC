package inputport.datacomm.simplex.buffer;
import java.nio.ByteBuffer;

import inputport.InputPort;
import inputport.datacomm.ReceiveListener;
import port.common.DistMisc;


public class AnEchoingBufferReceiveListener  implements ReceiveListener<ByteBuffer> {
	protected InputPort inputPort;
	public AnEchoingBufferReceiveListener (InputPort anInputPort) {
		inputPort = anInputPort;
	}
	protected  String computeResponse (String anInput) {
		return anInput;
	}
	
	protected  String computeResponse (ByteBuffer anInput) {
		return computeResponse(DistMisc.toString(anInput));
	}
	
//	protected ByteBuffer computeBufferResponse (ByteBuffer anInput) {
//		return ByteBuffer.wrap(computeStringResponse(anInput).getBytes());
//	}
	
	
	
	@Override
	public void messageReceived(String aRemoteEnd, ByteBuffer aMessage) {		
		System.out.println(
					inputPort.getLocalName() + "<--" +	aRemoteEnd + ":" +
							computeResponse(aMessage));
	}
}
