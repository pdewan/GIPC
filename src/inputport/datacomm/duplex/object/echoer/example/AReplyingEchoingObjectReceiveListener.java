package inputport.datacomm.duplex.object.echoer.example;
import inputport.InputPort;
import inputport.datacomm.duplex.DuplexInputPort;
import inputport.datacomm.duplex.buffer.echoer.example.AReplyingUpperCaseBufferReceiveListener;
import inputport.datacomm.simplex.object.example.AnUpperCaseObjectReceiveListener;
public class AReplyingEchoingObjectReceiveListener extends AnUpperCaseObjectReceiveListener   {
	public AReplyingEchoingObjectReceiveListener (InputPort anInputPort) {
		super(anInputPort);
	}

	@Override
	public void messageReceived(String aRemoteEnd, Object aMessage) {
		super.messageReceived(aRemoteEnd, aMessage);
		((DuplexInputPort) inputPort).reply(
				AReplyingUpperCaseBufferReceiveListener.computeReply(
						computeResponse((String) aMessage)));
	}
//	protected String computeResponse(String anInput) {
//		return anInput.toUpperCase();
//	}
}
