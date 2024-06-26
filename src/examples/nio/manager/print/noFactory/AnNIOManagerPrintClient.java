package examples.nio.manager.print.noFactory;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import inputport.nio.manager.NIOManager;
import inputport.nio.manager.NIOManagerFactory;
import inputport.nio.manager.listeners.SocketChannelConnectListener;
import inputport.nio.manager.listeners.SocketChannelWriteListener;
import util.trace.factories.FactoryTraceUtility;
import util.trace.port.nio.NIOTraceUtility;

public class AnNIOManagerPrintClient implements SocketChannelConnectListener, SocketChannelWriteListener {
	protected NIOManager nioManager = NIOManagerFactory.getSingleton();
	protected SocketChannel socketChannel;
	protected Scanner scanner = new Scanner(System.in);

	protected AnNIOManagerPrintClient(int aServerPort) {
		setTracing();
//		setFactories();
		initialize(aServerPort);
	}

//	protected void setFactories() {
//		ConnectCommandFactorySelector.setFactory(new AConnectCommandFactory(0));
//	}

	public void processInput() {
		scanner = new Scanner(System.in);
		while (true) {
			System.out.println("Enter a line of input to be printed remotely");
			String aNextLine = scanner.nextLine();
			// wrap writes to the buffer and then flips it
			ByteBuffer aWriteMessage = ByteBuffer.wrap(aNextLine.getBytes());
			nioManager.write(socketChannel, aWriteMessage, this);
		}
	}

	protected void setTracing() {
		FactoryTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
	}

	protected void initialize(int aServerPort) {
		try {
			socketChannel = SocketChannel.open();
			InetAddress aServerAddress = InetAddress.getByName("localhost");
			
			nioManager.connect(socketChannel, aServerAddress, aServerPort, 
					0, // do not allow any incoming messages
					this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// implementing connect listener with dummy methods
	@Override
	public void connected(SocketChannel aSocketChannel) {

	}
	@Override
	public void notConnected(SocketChannel theSocketChannel, Exception e) {

	}
	
	// implementing write listener with dummy methods
	@Override
	public void written(SocketChannel socketChannel, ByteBuffer aWriteBuffer, int sendId) {

	}
}
