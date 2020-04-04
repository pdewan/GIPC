package examples.nio.manager.meaning.server;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import assignments.util.mainArgs.ServerArgsProcessor;
import inputport.nio.manager.NIOManagerFactory;
import inputport.nio.manager.factories.classes.AReadingAcceptCommandFactory;
import inputport.nio.manager.factories.selectors.AcceptCommandFactorySelector;
import util.annotations.Tags;
import util.tags.DistributedTags;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import util.trace.port.nio.SocketChannelBound;
@Tags(DistributedTags.SERVER)
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

	
	protected void setFactories() {
		AcceptCommandFactorySelector.setFactory(new AReadingAcceptCommandFactory());
	}

	protected void makeServerConnectable(int aServerPort) {
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
//	@Override
//	public void writeBufferIsEmpty(SocketChannel aSocketChannel) {
//		NIOManagerFactory.getSingleton().enableReads(aSocketChannel);
//	}

	public static void main(String[] args) {
		FactoryTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		BeanTraceUtility.setTracing();// not really needed, but does not hurt
		MeaningOfLifeNIOServer aServer = new AMeaningOfLifeNIOServer();
		aServer.initialize(ServerArgsProcessor.getServerPort(args));

	}
}
