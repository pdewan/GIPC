package inputport.nio.manager.commands.classes;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.commands.AcceptCommand;
import inputport.nio.manager.listeners.SocketChannelAcceptListener;
import util.trace.Tracer;
import util.trace.port.nio.SocketChannelAccepted;
import util.trace.port.nio.SocketChannelBlockingConfigured;
import util.trace.port.nio.SocketChannelRegistered;


public class AnAcceptCommand extends AnAbstractNIOCommand implements AcceptCommand {
	InetAddress serverHost;
//	SocketChannelAcceptListener listener;
	private List<SocketChannelAcceptListener> listeners = new ArrayList();
	int port;
	int newOps;
	ServerSocketChannel serverSocketChannel;
	SelectionManager selectingRunnable;
	public AnAcceptCommand(SelectionManager aSelectingRunnable, 
			ServerSocketChannel aSocketChannel,
			Integer aNextInterestOps) {
		super (aSocketChannel, aNextInterestOps);	
		serverSocketChannel = aSocketChannel;
		selectingRunnable = aSelectingRunnable;
	}	
	public AnAcceptCommand(SelectionManager aSelectingRunnable, 
			ServerSocketChannel aSocketChannel) {
		this(aSelectingRunnable, aSocketChannel, null);
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
	protected void configureBlocking() {
		try {
			boolean aBlockingStatus = false;
			serverSocketChannel.configureBlocking(aBlockingStatus);
			SocketChannelBlockingConfigured.newCase(this, serverSocketChannel, aBlockingStatus);
//			serverSocketChannel.configureBlocking(false);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	@Override
	public boolean initiate() {
		try {
			Tracer.info(this, "Regisering  accept op for channel:" + serverSocketChannel);
			configureBlocking();
//			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.register(selectingRunnable.getSelector(), SelectionKey.OP_ACCEPT);
			SocketChannelRegistered.newCase(this, serverSocketChannel, selectingRunnable.getSelector(), SelectionKey.OP_ACCEPT);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public synchronized boolean execute() {
		try {
			SocketChannel newSocketChannel = serverSocketChannel.accept();
			SocketChannelAccepted.newCase(this, newSocketChannel, serverSocketChannel,listeners );
//			newSocketChannel.configureBlocking(false);	
			Tracer.info(this, "New socket channel with read ops enabled:" + newSocketChannel);
//			newSocketChannel.register(selectingRunnable.getSelector(), SelectionKey.OP_READ);			
//			try {
//			for (SocketChannelAcceptListener listener:listeners)
//				listener.socketChannelAccepted(serverSocketChannel, newSocketChannel);
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
			newSocketChannel.configureBlocking(false);
			Integer aNextInterestOps = nextInterestOps != null?nextInterestOps:0;
//			newSocketChannel.register(selectingRunnable.getSelector(), SelectionKey.OP_READ);	
			newSocketChannel.register(selectingRunnable.getSelector(), aNextInterestOps);	

			SocketChannelRegistered.newCase(this, newSocketChannel, selectingRunnable.getSelector(), aNextInterestOps);
			try {
				for (SocketChannelAcceptListener listener:listeners)
					listener.socketChannelAccepted(serverSocketChannel, newSocketChannel);
				} catch (Exception e) {
					e.printStackTrace();
				}
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	@Override
	public synchronized void addAcceptListener(SocketChannelAcceptListener aListener) {
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
