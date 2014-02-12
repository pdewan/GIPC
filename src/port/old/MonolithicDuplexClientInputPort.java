package port.old;

import inputport.InputPort;

import java.nio.ByteBuffer;



public interface MonolithicDuplexClientInputPort extends InputPort, MonolithicClientInputPort, ConnectionSendReceiptNotifier/*, DuplexSender*/{
	void reply(ByteBuffer message);
	void send(ByteBuffer message);
}
