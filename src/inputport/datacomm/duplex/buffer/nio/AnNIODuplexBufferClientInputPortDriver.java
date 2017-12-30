package inputport.datacomm.duplex.buffer.nio;

import inputport.datacomm.duplex.buffer.DuplexBufferGenericClientInputPort;
import inputport.datacomm.duplex.buffer.DuplexClientInputPortSkeleton;
import inputport.datacomm.simplex.buffer.nio.AnNIOSimplexBufferClientInputPortDriver;
import inputport.nio.manager.ConnectCommandSelector;
import inputport.nio.manager.SelectionManager;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;


public class AnNIODuplexBufferClientInputPortDriver extends AnNIOSimplexBufferClientInputPortDriver implements NIODuplexBufferClientInputPortDriver{
	DuplexClientInputPortSkeleton duplexSkeleton;
	public AnNIODuplexBufferClientInputPortDriver(SelectionManager theSelectingRunnable,
			String theRemoteHostName, String theRemotePort, String aServerName) {
		super(theSelectingRunnable, theRemoteHostName, theRemotePort, aServerName);	
		ConnectCommandSelector.setFactory(new AReadingWritingConnectCommandFactory());

	}
	@Override
	public void connected(SocketChannel theSocketChannel) {
		super.connected(theSocketChannel);
//		selectionManager.registerReadListener(theSocketChannel, this);
		observableNIOManager.addReadListener(theSocketChannel, this);
		observableNIOManager.addWriteBoundedBufferListener(theSocketChannel, this);
		
	}
	@Override
	public void socketChannelRead(SocketChannel theSocketChannel,
			ByteBuffer theMessage, int aLength) {
		duplexSkeleton.messageReceived(serverName, theMessage);		
	}	

	@Override
	public void setSkeleton(DuplexBufferGenericClientInputPort<SocketChannel> theSkeleton) {
		super.setSkeleton(theSkeleton);
		duplexSkeleton = theSkeleton;
	}
	@Override
	public void bufferIsEmpty(SocketChannel aSocketChannel) {
		observableNIOManager.enableReads(aSocketChannel);
		
	}

}
