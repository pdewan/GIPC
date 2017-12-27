package examples.nio;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;

import trace.port.nio.NIOTraceUtility;
import inputport.nio.AConnectCommandFactory;
import inputport.nio.AnObservableNIOManager;
import inputport.nio.ConnectCommandSelector;
import inputport.nio.ObservableNIOManager;

public class AMeaningOfLifeClient implements MeaningOfLifeClient{
	ObservableNIOManager nioManager;
	String clientName;
	public AMeaningOfLifeClient(String aServerHost, int aServerPort, String aClientName) {
		try {
		ConnectCommandSelector.setFactory(new AConnectCommandFactory());
		nioManager = new AnObservableNIOManager();
		clientName = aClientName;
		SocketChannel aSocketChannel = createSocketChannel();
		InetAddress aServerAddress = InetAddress.getByName(aServerHost);
		nioManager.connect(aSocketChannel, aServerAddress, aServerPort, this);
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	SocketChannel createSocketChannel() {
		try {
			SocketChannel retVal = SocketChannel.open();
			return retVal;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@Override
	public void connected(SocketChannel aSocketChannel) {
		Runnable aMeaningOfLifeRunnable = new AMeaningOfLifeSender(nioManager, aSocketChannel, clientName);
		Thread aMeaningOfLifeThread = new Thread(aMeaningOfLifeRunnable);
		aMeaningOfLifeThread.start();
	}
	@Override
	public void notConnected(SocketChannel theSocketChannel, Exception e) {
		e.printStackTrace();
		
	}
	public static void main (String[] args) {
		NIOTraceUtility.setTracing();
		new AMeaningOfLifeClient("localhost", ServerPort.SERVER_PORT, "Client");
		
	}
}
