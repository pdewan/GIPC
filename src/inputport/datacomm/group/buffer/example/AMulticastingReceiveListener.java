package inputport.datacomm.group.buffer.example;

import inputport.datacomm.duplex.buffer.echoer.example.AReplyingUpperCaseBufferReceiveListener;
import inputport.datacomm.group.GroupInputPort;

import java.nio.ByteBuffer;


public class AMulticastingReceiveListener extends AReplyingUpperCaseBufferReceiveListener {
//	GroupInputPort<ByteBuffer> inputPort;
	public AMulticastingReceiveListener (GroupInputPort<ByteBuffer> anInputPort) {
		super(anInputPort);
		inputPort = anInputPort;
	}
	@Override
	public void messageReceived(String aRemoteEnd, ByteBuffer aMessage) {
		ByteBuffer retMessage = ByteBuffer.wrap(aMessage.array(), aMessage.position(), aMessage.limit() - aMessage.position());
		// can use sendAll also
//		inputPort.reply(ByteBuffer.wrap(aMessage.array(), 0, aMessage.limit()));
		super.messageReceived(aRemoteEnd, aMessage);
		// the super call will send the buffer, so create a new buffer
		((GroupInputPort) inputPort).sendOthers(retMessage);
	}

}
