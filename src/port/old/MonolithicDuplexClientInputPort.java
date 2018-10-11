package port.old;

import java.nio.ByteBuffer;

import inputport.InputPort;



public interface MonolithicDuplexClientInputPort extends InputPort, MonolithicClientInputPort, ConnectionSendReceiptNotifier/*, DuplexSender*/{
	void reply(ByteBuffer message);
	void send(ByteBuffer message);
}
