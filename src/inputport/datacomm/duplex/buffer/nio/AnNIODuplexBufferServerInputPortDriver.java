package inputport.datacomm.duplex.buffer.nio;

import inputport.datacomm.duplex.SendToUnkonwnRemoteNameException;
import inputport.datacomm.duplex.buffer.DuplexServerInputPortSkeleton;
import inputport.datacomm.simplex.buffer.nio.AnNIOSimplexBufferServerInpuPorttDriver;
import inputport.nio.SelectionManager;

import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


public class AnNIODuplexBufferServerInputPortDriver extends AnNIOSimplexBufferServerInpuPorttDriver 
             implements NIODuplexBufferServerInputPortDriver {

	DuplexServerInputPortSkeleton<ServerSocketChannel, SocketChannel> duplexSkeleton;



	public AnNIODuplexBufferServerInputPortDriver(SelectionManager theSelectingRunnable, String thePortId) {
		super(theSelectingRunnable, thePortId);
	}

	@Override
	public void setSkeleton(DuplexServerInputPortSkeleton theSkeleton) {
		super.setSkeleton(theSkeleton);
		duplexSkeleton = theSkeleton;
		
	}

	
	@Override
	public void send(String aDestination, ByteBuffer message) {	
		// repeating code in each driver but gives more unformity as each kind of sink implements the same interface
		SocketChannel aSocketChannel = duplexSkeleton.getChannel(aDestination);
		if (aSocketChannel == null) throw new SendToUnkonwnRemoteNameException();
		observableNIOManager.write(aSocketChannel, message, this);
//		WriteCommand bufferedWrite = new AWriteCommand(selectionManager, aSocketChannel, message, this, this);
//		selectionManager.putBufferedWrite(bufferedWrite);		
	}
	@Override
	public void written(SocketChannel socketChannel,
			ByteBuffer theWriteBuffer, int sendId) {
		duplexSkeleton.messageSent(duplexSkeleton.getClientName(socketChannel), theWriteBuffer, sendId);
		
	}



	

	

}
