package inputport.datacomm.simplex.buffer.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

import util.trace.Tracer;



public class AConnectCommand implements ConnectCommand {
	InetAddress serverHost;
//	SocketChannelConnectListener listener;
	List<SocketChannelConnectListener> listeners = new ArrayList();
	int port;
	SocketChannel socketChannel;
	boolean connected;
	SelectionManager selectionManager;
	public AConnectCommand(SelectionManager theSelectionManager, SocketChannel theSocketChannel, InetAddress theServerHost, int thePort) {
		serverHost = theServerHost;
//		if (theListener != null)
//			addConnectListener(theListener);
		socketChannel = theSocketChannel;
		port = thePort;
		selectionManager = theSelectionManager;
	}	
	public InetAddress getServerHost() {
		return serverHost;
	}
	public int getPort() {
		return port;
	}
	
	@Override
	public boolean initiate() {
		try {
//			Tracer.info(this, "Making request");
			// unless there is a gap between two connection requests, the second one fails with no warning
			// configuring the mode as blockng throws exception
			// only solution maybe to throw a sleep in here or in the program that calls connect.			
//			socketChannel.configureBlocking(false);
//			socketChannel.configureBlocking(true); // asynchrony is causing problems
			socketChannel.configureBlocking(false);
			Tracer.info(this, "Making connect request for host " + serverHost + " port " + port);
			connected = socketChannel.connect(new InetSocketAddress(serverHost, port));
			Tracer.info(this, "Connection status for  " + socketChannel + " is " + connected);
			if (connected) {
				doGiveReponse();
			}
			else {

			Tracer.info(this, "Registering connect op for:" + socketChannel );
			socketChannel.register(selectionManager.getSelector(), SelectionKey.OP_CONNECT);
			}
//			connected = socketChannel.connect(new InetSocketAddress(serverHost, port));
			return connected;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	void doGiveReponse() {
		SelectionKey selectionKey = socketChannel.keyFor(selectionManager.getSelector());		
//		connected = true;
		Tracer.info(this, "Registering read op for:" + socketChannel );
//		selectionKey.interestOps(SelectionKey.OP_READ);
		Tracer.info(this, "New ops for:" + socketChannel + " are "  + selectionKey.interestOps());
		for (SocketChannelConnectListener listener:listeners)
			listener.connected(socketChannel);
		selectionKey.interestOps(SelectionKey.OP_READ);

	}
	@Override
	public boolean execute() {
		try { 
			if (connected) return true;
//			Tracer.info(this, "Giving response for socket channel: " + socketChannel);
			connected = socketChannel.finishConnect();
//			Message.info("Finish connections status  for channel "  + socketChannel + " "  + connected);
			if (!connected) {
				Tracer.error("Could not finish connection for channel " + socketChannel);
				return false;
			}
			try {
			doGiveReponse();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
//			SelectionKey selectionKey = socketChannel.keyFor(selectingRunnable.getSelector());		
//			Tracer.info(this, "Registering read op for:" + socketChannel );
//			selectionKey.interestOps(SelectionKey.OP_READ);
//			Tracer.info(this, "New ops for:" + socketChannel + " are "  + selectionKey.interestOps());
//			if (listener != null)
//				listener.connected(socketChannel);
		} catch (Exception e) {
			for (SocketChannelConnectListener listener:listeners)
				listener.notConnected(socketChannel, e);
//			else
//				e.printStackTrace();
			return false;
		}
	}
	@Override
	public void addConnectListener(SocketChannelConnectListener aListener) {
		listeners.add(aListener);
	}
	@Override
	public SelectableChannel getChannel() {
		return socketChannel;
	}

}
