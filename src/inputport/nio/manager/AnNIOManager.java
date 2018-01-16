package inputport.nio.manager;

import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import util.trace.Tracer;
import util.trace.port.nio.ListenableAcceptsEnabled;
import util.trace.port.nio.ReadListenerAdded;
import util.trace.port.nio.ReadsEnabled;
import util.trace.port.nio.WriteListenerAdded;


public class AnNIOManager implements NIOManager{
	SelectionManager selectionManager;
	boolean allowReads;
	
	public AnNIOManager(SelectionManager aSelectionManager) {
		selectionManager = aSelectionManager;
	}
	public AnNIOManager() {
		selectionManager = SelectionManagerFactory.getSelectionManager();
	}
//	public AnObservableNIOManager() {
//		selectionManager = SelectionManagerFactory.getSelectionManager();
//	}
	
	public void enableListenableAccepts(ServerSocketChannel channel,
			SocketChannelAcceptListener... listeners) {
		ListenableAcceptsEnabled.newCase(this, channel, listeners);
		AcceptCommand acceptRequestResponse = 
				AcceptCommandSelector.getFactory().createAcceptCommand(selectionManager, channel);
//			new AnAcceptCommand(selectionManager, channel);
		for (SocketChannelAcceptListener listener:listeners) {
			acceptRequestResponse.addAcceptListener(listener);
		}
		selectionManager.putRequestResponse(acceptRequestResponse);	
		Tracer.info(this, "Waking up selector to process newly queued accept request");
		
	}
	@Override
	public void connect(SocketChannel channel, InetAddress theServerHost,
			int thePort, SocketChannelConnectListener... listeners) {
		ConnectCommand connectCommand = 
//			new AConnectCommand(selectionManager, channel, theServerHost, thePort);
		ConnectCommandSelector.getFactory().createConnectCommand(selectionManager, channel, theServerHost, thePort);
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
		Tracer.info(this, "Created buffered write and associated it with listeners");
		selectionManager.putBufferedWrite(bufferedWrite);		
	}

	@Override
	public void addReadListener(SocketChannel channel,
			SocketChannelReadListener listener) {
		ReadListenerAdded.newCase(this, channel, listener);
		selectionManager.getReadHandler(channel).addReadListener(listener);
	}
	@Override
	public void addCloseListener(SocketChannel channel,
			SocketChannelCloseListener listener) {
		selectionManager.getReadHandler(channel).addCloseListener(listener);
		
	}
//	@Override
//	public void enableAccepts(ServerSocketChannel channel,
//			SocketChannelAcceptListener listener) {
//		
//		SocketChannelAcceptListener[] listeners = {};
//		if (listener !=null) {
//			listeners = new SocketChannelAcceptListener[] {listener};
//		}
//			
//		doAccept (channel, listeners);
//		
//	}
	
//	@Override
//	public void connect(SocketChannel channel, InetAddress theServerHost,
//			int thePort, SocketChannelConnectListener listener) {
//		SocketChannelConnectListener[] listeners = {listener};
//		connect(channel, theServerHost, thePort, listeners);		
//	}
	
//	@Override
//	public void write(SocketChannel channel, ByteBuffer byteBuffer,
//			SocketChannelWriteListener listener) {
//		SocketChannelWriteListener[] listeners = {listener};
//		write(channel, byteBuffer, listeners);		
//	}
	@Override
	public void enableReads(SocketChannel aChannel) {
		ReadsEnabled.newCase(this, aChannel);
		selectionManager.getReadHandler(aChannel).initiate();
	}
	@Override
	public void addWriteBoundedBufferListener(SocketChannel aSocketChannel, WriteBoundedBufferListener aListener) {
		WriteListenerAdded.newCase(this, aSocketChannel, aListener);
		selectionManager.getWriteBoundedBuffer(aSocketChannel).addListener(aListener);
	}
//	@Override
//	public void bufferIsEmpty(SelectionManager aSelectionManager,
//			SocketChannel aSocketChannel) {
//		if (!isAllowReads()) {
//			return;
//		}
//		enableReads(aSocketChannel);		
//	}
//	@Override
//	public boolean isAllowReads() {
//		return allowReads;
//	}
//	@Override
//	public void setAllowReads(boolean allowReads) {
//		this.allowReads = allowReads;
//	}
}
