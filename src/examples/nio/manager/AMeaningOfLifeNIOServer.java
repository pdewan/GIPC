package examples.nio.manager;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import javax.net.ServerSocketFactory;

import trace.port.nio.NIOTraceUtility;
import trace.port.nio.SocketChannelBound;
import inputport.datacomm.simplex.buffer.nio.AReadingAcceptCommandFactory;
import inputport.nio.manager.AcceptCommandSelector;
import inputport.nio.manager.AnObservableNIOManager;
import inputport.nio.manager.ObservableNIOManager;
import inputport.nio.manager.ObservableNIOManagerFactory;
import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.SelectionManagerFactory;

public class AMeaningOfLifeNIOServer implements MeaningOfLifeNIOServer {
	
	public AMeaningOfLifeNIOServer(int aServerPort) {
			AcceptCommandSelector.setFactory(new AReadingAcceptCommandFactory());			
			ServerSocketChannel aServerSocketChannel  = createSocketChannel(aServerPort);
			ObservableNIOManagerFactory.getSingleton().enableAccepts(aServerSocketChannel, this);
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
	@Override
	public void socketChannelAccepted(ServerSocketChannel aServerSocketChannel,
			SocketChannel aSocketChannel) {
		MeaningOfLifeReceiver aMeaningOfLifeReceiver = new AMeaningOfLfeServerReceiver();
		ObservableNIOManagerFactory.getSingleton().addReadListener(aSocketChannel, aMeaningOfLifeReceiver);		
	}	
    public static void main(String[] args) {
    	NIOTraceUtility.setTracing();
    	new AMeaningOfLifeNIOServer(SERVER_PORT);		
	}
}
