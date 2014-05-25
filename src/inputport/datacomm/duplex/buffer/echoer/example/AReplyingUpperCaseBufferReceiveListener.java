package inputport.datacomm.duplex.buffer.echoer.example;
import inputport.InputPort;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.simplex.buffer.example.AnUpperCaseBufferReceiveListener;

import java.nio.ByteBuffer;

public class AReplyingUpperCaseBufferReceiveListener extends AnUpperCaseBufferReceiveListener  {
	public AReplyingUpperCaseBufferReceiveListener (InputPort anInputPort) {
		super(anInputPort);
	}
	

	public static  String computeReply(String anInput) {
		return "You said:" + anInput;
	}

	@Override
	public void messageReceived(String aRemoteEnd, ByteBuffer aMessage) {
		super.messageReceived(aRemoteEnd, aMessage);
		((DuplexInputPort<ByteBuffer>) inputPort).reply(ByteBuffer.wrap(
				computeReply(
						computeResponse(aMessage)).getBytes()));

	}
}
