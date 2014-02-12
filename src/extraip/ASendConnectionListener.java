package extraip;

import inputport.InputPort;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;

import port.ATracingConnectionListener;


public class ASendConnectionListener extends ATracingConnectionListener implements ByteBufferSendListener {
		public ASendConnectionListener(
			InputPort anInputPort) {
		super(anInputPort);
		}

		@Override
	public void messageSent(String aRemoteEnd, ByteBuffer message, int sentId) {
		System.out.println("sent message with id " + sentId);
	}

}
