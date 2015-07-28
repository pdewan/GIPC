package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

public interface EchoingBufferSender extends Runnable{

	public static final String LOCAL_SENDER = "Local Sender";


	public abstract void enqueLocalSend(ByteBuffer aMessage);


	void localSend(ByteBuffer aMessage);

}