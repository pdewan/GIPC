package port.old;

import java.nio.ByteBuffer;

import inputport.datacomm.Sender;



public interface UniByteBufferSend extends Sender<ByteBuffer>{
	void send(String remoteName, ByteBuffer message);
	void send (ByteBuffer message);

}
