package inputport.datacomm.simplex.buffer.nio;

import java.net.InetAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import port.trace.nio.SocketChannelAccepted;
import port.trace.nio.SocketChannelInterestOp;
import port.trace.nio.SocketChannelRegistered;
import util.trace.Tracer;


public class AnAcceptCommand implements AcceptCommand {
	InetAddress serverHost;
//	SocketChannelAcceptListener listener;
	List<SocketChannelAcceptListener> listeners = new ArrayList();
	int port;
	int newOps;
	ServerSocketChannel serverSocketChannel;
	SelectionManager selectingRunnable;
	public AnAcceptCommand(SelectionManager theSelectingRunnable, ServerSocketChannel theSocketChannel) {
	
		serverSocketChannel = theSocketChannel;
		selectingRunnable = theSelectingRunnable;
	}	
	public InetAddress getServerHost() {
		return serverHost;
	}
	public int getPort() {
		return port;
	}
//	@Override
//	public SocketChannelAcceptListener getListener() {
//		return listener;
//	}
	@Override
	public boolean initiate() {
		try {
			Tracer.info(this, "Regisering  accept op for channel:" + serverSocketChannel);
			serverSocketChannel.configureBlocking(false);

			serverSocketChannel.register(selectingRunnable.getSelector(), SelectionKey.OP_ACCEPT);
			SocketChannelRegistered.newCase(this, serverSocketChannel, selectingRunnable.getSelector(), SelectionKey.OP_ACCEPT);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public boolean execute() {
		try {
			SocketChannel newSocketChannel = serverSocketChannel.accept();
			SocketChannelAccepted.newCase(this, newSocketChannel, serverSocketChannel);
//			newSocketChannel.configureBlocking(false);	
			Tracer.info(this, "New socket channel with read ops enabled:" + newSocketChannel);
//			newSocketChannel.register(selectingRunnable.getSelector(), SelectionKey.OP_READ);			
			for (SocketChannelAcceptListener listener:listeners)
				listener.socketChannelAccepted(serverSocketChannel, newSocketChannel);
			newSocketChannel.configureBlocking(false);
			newSocketChannel.register(selectingRunnable.getSelector(), SelectionKey.OP_READ);	
			SocketChannelRegistered.newCase(this, newSocketChannel, selectingRunnable.getSelector(), SelectionKey.OP_READ);

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public void addAcceptListener(SocketChannelAcceptListener aListener) {
		listeners.add(aListener);
	}
	
	@Override
	public SelectableChannel getChannel() {
		return serverSocketChannel;
	}
//	@Override
//	public SelectingRunnable getSelectingRunnable() {
//		return getSelectingRunnable();
//	}
}
