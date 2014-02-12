package inputport.datacomm.simplex.buffer.nio;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import util.trace.Tracer;


public class AnObservableNIOManager implements ObservableNIOManager{
	SelectionManager selectionManager;
	public AnObservableNIOManager(SelectionManager aSelectionManager) {
		selectionManager = aSelectionManager;
	}
	public AnObservableNIOManager() {
		selectionManager = ASelectionManagerManager.getSelectionManager();
	}
	@Override
	public void accept(ServerSocketChannel channel,
			SocketChannelAcceptListener[] listeners) {
		AcceptCommand acceptRequestResponse = 
			new AnAcceptCommand(selectionManager, channel);
		for (SocketChannelAcceptListener listener:listeners) {
			acceptRequestResponse.addAcceptListener(listener);
		}
		selectionManager.putRequestResponse(acceptRequestResponse);	
		Tracer.info(this, "Waking up selector to process newly queued accept request");
		
	}
	@Override
	public void connect(SocketChannel channel, InetAddress theServerHost,
			int thePort, SocketChannelConnectListener[] listeners) {
		ConnectCommand connectCommand = 
			new AConnectCommand(selectionManager, channel, theServerHost, thePort);
		for (SocketChannelConnectListener listener:listeners) {
			connectCommand.addConnectListener(listener);
		}
		selectionManager.putRequestResponse(connectCommand);
	}
	
	
	@Override
	public void write(SocketChannel channel, ByteBuffer byteBuffer,
			SocketChannelWriteListener[] listeners) {
		WriteCommand bufferedWrite = new AWriteCommand(selectionManager, channel, byteBuffer);
		for (SocketChannelWriteListener listener:listeners) {
			bufferedWrite.addwriteListener(listener);
		}
		Tracer.info(this, "Created bufferd write and associated it with listeners");
		selectionManager.putBufferedWrite(bufferedWrite);		
	}

	@Override
	public void addReadListener(SocketChannel channel,
			SocketChannelReadListener listener) {
		selectionManager.getReadHandler(channel).addReadListener(listener);
	}
	@Override
	public void addCloseListener(SocketChannel channel,
			SocketChannelCloseListener listener) {
		selectionManager.getReadHandler(channel).addCloseListener(listener);
		
	}
	@Override
	public void accept(ServerSocketChannel channel,
			SocketChannelAcceptListener listener) {
		SocketChannelAcceptListener[] listeners = {listener};
		accept (channel, listeners);
		
	}
	
	@Override
	public void connect(SocketChannel channel, InetAddress theServerHost,
			int thePort, SocketChannelConnectListener listener) {
		SocketChannelConnectListener[] listeners = {listener};
		connect(channel, theServerHost, thePort, listeners);		
	}
	

	

	

	

	@Override
	public void write(SocketChannel channel, ByteBuffer byteBuffer,
			SocketChannelWriteListener listener) {
		SocketChannelWriteListener[] listeners = {listener};
		write(channel, byteBuffer, listeners);		
	}

	
	

}
