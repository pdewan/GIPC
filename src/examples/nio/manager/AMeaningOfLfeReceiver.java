package examples.nio.manager;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class AMeaningOfLfeReceiver implements MeaningOfLifeReceiver{

	@Override
	public void socketChannelRead(SocketChannel aSocketChannel,
			ByteBuffer aMessage, int aLength) {
		 String aMeaning = new String(aMessage.array(), aMessage.position(), aLength);
		System.out.println ("Meaning of Life from " + aMeaning);		
	}

}
