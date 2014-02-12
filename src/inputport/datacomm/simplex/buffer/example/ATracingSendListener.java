package inputport.datacomm.simplex.buffer.example;

import inputport.InputPort;
import inputport.datacomm.simplex.buffer.ByteBufferSendListener;

import java.nio.ByteBuffer;
public class ATracingSendListener  implements ByteBufferSendListener {
	InputPort inputPort;
	public ATracingSendListener(InputPort anInputPort) {
		inputPort = anInputPort;
	}
	@Override
	public void messageSent(String aRemoteEnd, ByteBuffer aMessage, int aSentId) {
		System.out.println(inputPort.getLocalName() + 
		"-->"  + aRemoteEnd + 
		":(" + aSentId + ")" + 	aMessage);
	}
}
