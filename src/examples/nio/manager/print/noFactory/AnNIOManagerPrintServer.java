package examples.nio.manager.print.noFactory;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;


import inputport.nio.manager.NIOManager;
import inputport.nio.manager.NIOManagerFactory;
import inputport.nio.manager.factories.classes.AReadingAcceptCommandFactory;
import inputport.nio.manager.factories.classes.AnAcceptCommandFactory;
import inputport.nio.manager.factories.selectors.AcceptCommandFactorySelector;
import inputport.nio.manager.listeners.SocketChannelAcceptListener;
import inputport.nio.manager.listeners.SocketChannelReadListener;
import inputport.nio.manager.listeners.SocketChannelWriteListener;
import inputport.nio.manager.listeners.WriteBoundedBufferListener;
import util.trace.factories.FactoryTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.nio.SocketChannelBound;

public class AnNIOManagerPrintServer implements NIOManagerPrintServer {
	protected NIOManager nioManager = NIOManagerFactory.getSingleton();
	public AnNIOManagerPrintServer(int aServerPort) {
		setTracing();
//		setFactories();
		initialize(aServerPort);
	}
//	protected void setFactories() {
//		AcceptCommandFactorySelector.setFactory(new AnAcceptCommandFactory(SelectionKey.OP_READ));
//	}
	protected void setTracing() {
		FactoryTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
	}
	protected void initialize(int aServerPort) {
		try {
			ServerSocketChannel aServerFactoryChannel = ServerSocketChannel.open();
			InetSocketAddress anInternetSocketAddress = new InetSocketAddress(aServerPort);
			aServerFactoryChannel.socket().bind(anInternetSocketAddress);
			SocketChannelBound.newCase(this, aServerFactoryChannel, anInternetSocketAddress);		
			nioManager.enableListenableAccepts(aServerFactoryChannel,
					SelectionKey.OP_READ, // allow incoming writes that can be read
					this);

		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	@Override
	public void socketChannelAccepted(ServerSocketChannel aServerSocketChannel, SocketChannel aSocketChannel) {
		nioManager.addReadListener(aSocketChannel, this);
		
	}
	@Override
	public void socketChannelRead(SocketChannel aSocketChannel, ByteBuffer aMessage, int aLength) {
		String aMessageString = new String(aMessage.array(), aMessage.position(),
				aLength);
		System.out.println(aMessageString + "<--" + aSocketChannel);
		
	}
	

}
