package port.old;

import inputport.datacomm.Sender;

import java.nio.ByteBuffer;



public interface UniByteBufferSend extends Sender<ByteBuffer>{
	void send(String remoteName, ByteBuffer message);
	void send (ByteBuffer message);

}
