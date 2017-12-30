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
import inputport.nio.manager.SelectionManager;
import inputport.nio.manager.SelectionManagerFactory;

public class AMeaningOfLifeNIOServer implements MeaningOfLifeNIOServer {
	ObservableNIOManager nioManager;
	
	public AMeaningOfLifeNIOServer(int aServerPort) {
			AcceptCommandSelector.setFactory(new AReadingAcceptCommandFactory());			
			ServerSocketChannel aServerSocketChannel  = createSocketChannel(aServerPort);
			nioManager = new AnObservableNIOManager();
			nioManager.enableAccepts(aServerSocketChannel, this);
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
//	@Override
//	public void socketChannelRead(SocketChannel aSocketChannel,
//			ByteBuffer aMessage, int aLength) {
////		byte[] aMyBytes = new byte[aLength];
////		 System.arraycopy(aMessage, aMessage.position(), aMyBytes, 0, aLength);
//
////		String aMeaning = new String(aMessage.array());
//		 String aMeaning = new String(aMessage.array(), aMessage.position(), aLength);
//		System.out.println ("Meaning of Life from " + aMeaning);		
//	}
	@Override
	public void socketChannelAccepted(ServerSocketChannel aServerSocketChannel,
			SocketChannel aSocketChannel) {
		MeaningOfLifeReceiver aMeaningOfLifeReceiver = new AMeaningOfLfeReceiver();
		nioManager.addReadListener(aSocketChannel, aMeaningOfLifeReceiver);
		
	}
	
	
    public static void main(String[] args) {
    	NIOTraceUtility.setTracing();
    	new AMeaningOfLifeNIOServer(SERVER_PORT);
		
	}
	
	

}
