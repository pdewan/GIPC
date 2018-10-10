package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

import util.trace.Tracer;
import util.trace.port.buffer.BufferLocalSendFinished;
import util.trace.port.buffer.BufferLocalSendInitiated;

public class AnEchoingBufferSender implements  EchoingBufferSender{
	// why only sever port? something crazy
	// actually it makese sense, a client should not be sending a message to itself
//	AGenericDuplexBufferServerInputPort serverPort; 
	DuplexBufferInputPort bufferPort; // 


	BlockingQueue<ByteBuffer> sentBuffers = new LinkedBlockingDeque<>();
	String portName;
	public AnEchoingBufferSender (DuplexBufferInputPort aServerPort) {
		bufferPort = aServerPort;
		portName = aServerPort.getLocalName();
		Thread aBufferSenderThread = new Thread(this);
		aBufferSenderThread.setName(LOCAL_SENDER);
		aBufferSenderThread.start();
	}

	
	@Override
	public void run() {
		while (true) {
			ByteBuffer aMessage;
			try {
				aMessage = sentBuffers.take();
				Tracer.info(this, "Doing local send of enqueued message:" + aMessage);
				localSend(aMessage);
//				serverPort.messageReceived(portName, aMessage);
//				serverPort.notifyPortSend(portName, aMessage, -1); // no channel wrie was actually done, this is to inform the serializer pool
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	@Override
	public void localSend(ByteBuffer aMessage) {
		BufferLocalSendFinished.newCase(this, LOCAL_SENDER, LOCAL_SENDER, aMessage, sentBuffers);


		bufferPort.messageReceived(portName, aMessage); 
		bufferPort.notifyPortSend(portName, aMessage, -1); // no channel wrie was actually done, this is to inform the serializer 
	}
	@Override
	public void enqueLocalSend(ByteBuffer aMessage) {
		Tracer.info(this, "Enqueing byte buffer for local send:" + aMessage);
		BufferLocalSendInitiated.newCase(this, LOCAL_SENDER, LOCAL_SENDER, aMessage, sentBuffers);
		sentBuffers.offer(aMessage);
		
	}
}
