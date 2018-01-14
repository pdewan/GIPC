package examples.nio.manager.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import javax.net.ServerSocketFactory;

import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.nio.SocketChannelBound;
import inputport.datacomm.simplex.buffer.nio.AReadingAcceptCommandFactory;
import inputport.nio.manager.AcceptCommandSelector;
import inputport.nio.manager.AnNIOManager;
import inputport.nio.manager.NIOManager;
import inputport.nio.manager.NIOManagerFactory;
import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.SelectionManagerFactory;

public class AMeaningOfLifeNIOServer implements MeaningOfLifeNIOServer {
	MeaningOfLifeServerReceiver meaningOfLifeReceiver;
	ServerSocketChannel serverSocketChannel;
	public AMeaningOfLifeNIOServer() {

	}

	protected void createCommunicationObjects() {
		createReceiver();
	}
	protected void createReceiver() {
		meaningOfLifeReceiver = new AMeaningOfLifeServerReceiver();
	}

	// public AMeaningOfLifeNIOServer(int aServerPort) {
	// AcceptCommandSelector.setFactory(new AReadingAcceptCommandFactory());
	// ServerSocketChannel aServerSocketChannel =
	// createSocketChannel(aServerPort);
	// NIOManagerFactory.getSingleton().enableAccepts(aServerSocketChannel,
	// this);
	// }
	protected void setFactories() {
		AcceptCommandSelector.setFactory(new AReadingAcceptCommandFactory());
	}

	protected void makeServerConnectable(int aServerPort) {
//		ServerSocketChannel aServerSocketChannel = createSocketChannel(aServerPort);
		NIOManagerFactory.getSingleton().enableListenableAccepts(
				serverSocketChannel, this);
	}

	public void initialize(int aServerPort) {
		setFactories();		
		serverSocketChannel = createSocketChannel(aServerPort);
		createCommunicationObjects();
		makeServerConnectable(aServerPort);
	}

	protected ServerSocketChannel createSocketChannel(int aServerPort) {
		try {
			ServerSocketChannel retVal = ServerSocketChannel.open();
			InetSocketAddress isa = new InetSocketAddress(aServerPort);
			retVal.socket().bind(isa);
			SocketChannelBound.newCase(this, retVal, isa);
			return retVal;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

//	protected void addWriteBufferListener(SocketChannel aSocketChannel) {
//		NIOManagerFactory.getSingleton().addWriteBoundedBufferListener(
//				aSocketChannel, this);
//	}

	protected void addReadListener(SocketChannel aSocketChannel) {
		NIOManagerFactory.getSingleton().addReadListener(aSocketChannel,
				meaningOfLifeReceiver);
	}

	protected void addListeners(SocketChannel aSocketChannel) {
//		addWriteBufferListener(aSocketChannel);
		addReadListener(aSocketChannel);		
	}
	@Override
	public void socketChannelAccepted(ServerSocketChannel aServerSocketChannel,
			SocketChannel aSocketChannel) {
		addListeners(aSocketChannel);
	}
	@Override
	public void writeBufferIsEmpty(SocketChannel aSocketChannel) {
		NIOManagerFactory.getSingleton().enableReads(aSocketChannel);
	}

	public static void main(String[] args) {
		NIOTraceUtility.setTracing();
		MeaningOfLifeNIOServer aServer = new AMeaningOfLifeNIOServer();
		aServer.initialize(ServerPort.SERVER_PORT);

	}
}
