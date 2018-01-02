package examples.nio.manager;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;

import trace.port.nio.NIOTraceUtility;
import inputport.nio.manager.AConnectCommandFactory;
import inputport.nio.manager.AnNIOManager;
import inputport.nio.manager.ConnectCommandSelector;
import inputport.nio.manager.NIOManager;
import inputport.nio.manager.NIOManagerFactory;

public class AMeaningOfLifeNIOClient implements MeaningOfLifeNIOClient {
	// ObservableNIOManager nioManager;
	String clientName;
	MeaningOfLifeModel meaningOfLifeModel;
	MeaningOfLifeController meaningOfLifeController;
	MeaningOfLifeView meaningOfLifeView;
	MeaningOfLifeClientSender meaningOfLifeSender;
	SocketChannel socketChannel;

	public AMeaningOfLifeNIOClient(String aClientName) {
		clientName = aClientName;
	}
	protected void setFactories() {		
		ConnectCommandSelector.setFactory(new AConnectCommandFactory());
	}
	public void initialize(String aServerHost, int aServerPort) {		
		createModel();
		setFactories();
		connectToServer(aServerHost, aServerPort);
		createUI();
	}

	public void createModel() {
		meaningOfLifeModel = new AMeaningOfLifeModel();
	}

	public void createUI() {
		meaningOfLifeView = new AMeaningOfLifeView();
		meaningOfLifeModel.addPropertyChangeListener(meaningOfLifeView);
		meaningOfLifeController = new AMeaningOfLifeController(
				meaningOfLifeModel);
		meaningOfLifeController.processInput();
	}

	public void processInput() {
		meaningOfLifeController.processInput();
	}	

	public void connectToServer(String aServerHost, int aServerPort) {
		socketChannel = createSocketChannel();
		// no listeners need to be registered, assuming writes go through
		connectToSocketChannel(aServerHost, aServerPort);

	}

	protected void connectToSocketChannel(String aServerHost, int aServerPort) {
		try {
			InetAddress aServerAddress = InetAddress.getByName(aServerHost);
			NIOManagerFactory.getSingleton().connect(socketChannel,
					aServerAddress, aServerPort, this);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected SocketChannel createSocketChannel() {
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
		createSender(aSocketChannel);
//		meaningOfLifeSender = new AMeaningOfLifeClientSender(aSocketChannel,
//				clientName);
//		meaningOfLifeModel.addPropertyChangeListener(meaningOfLifeSender);
	}
	protected void createCommunicationObjects(SocketChannel aSocketChannel) {
		createSender(aSocketChannel);
	}
	
	protected void createSender(SocketChannel aSocketChannel) {
		meaningOfLifeSender = new AMeaningOfLifeClientSender(aSocketChannel,
				clientName);
		meaningOfLifeModel.addPropertyChangeListener(meaningOfLifeSender);
	}
	@Override
	public void notConnected(SocketChannel aSocketChannel, Exception e) {
		System.err.println("Could not connect:" +aSocketChannel);
		if (e != null)
		   e.printStackTrace();
	}

	public static void launchClient(String aServerHost, int aServerPort,
			String aClientName) {
		MeaningOfLifeNIOClient aClient = new AMeaningOfLifeNIOClient(
				aClientName);
		aClient.initialize(aServerHost, aServerPort);		
	}


	public static void main(String[] args) {
		NIOTraceUtility.setTracing();
		launchClient(ClientArgsProcessor.chooseServerHost(args),
				ServerPort.SERVER_PORT,
				ClientArgsProcessor.chooseClientName(args));

	}
}
