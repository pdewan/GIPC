package examples.nio.manager.client;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;

import assignments.util.mainArgs.ClientArgsProcessor;
import assignments.util.mainArgs.ServerPort;
import examples.nio.manager.mvc.AMeaningOfLifeController;
import examples.nio.manager.mvc.AMeaningOfLifeModel;
import examples.nio.manager.mvc.AMeaningOfLifeView;
import examples.nio.manager.mvc.MeaningOfLifeController;
import examples.nio.manager.mvc.MeaningOfLifeModel;
import examples.nio.manager.mvc.MeaningOfLifeView;
import util.annotations.Tags;
import util.tags.DistributedTags;
import util.trace.bean.BeanTraceUtility;
import util.trace.factories.FactoryTraceUtility;
import util.trace.port.nio.NIOTraceUtility;
import inputport.nio.manager.AnNIOManager;
import inputport.nio.manager.NIOManager;
import inputport.nio.manager.NIOManagerFactory;
import inputport.nio.manager.factories.classes.AConnectCommandFactory;
import inputport.nio.manager.factories.selectors.ConnectCommandFactorySelector;
/**
 * Performs client tasks together with the helper listener (observer) class - AMeaningOfLifeClientSender.
 * The entry point is launchClient.
 * Implements some of  mundane listener tasks itself - look at its interface to see which listeners.
 * Can be further modularized by having separate helper listeners.
 */
@Tags(DistributedTags.CLIENT)
public class AMeaningOfLifeNIOClient implements MeaningOfLifeNIOClient {
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
		ConnectCommandFactorySelector.setFactory(new AConnectCommandFactory());
	}
	public void initialize(String aServerHost, int aServerPort) {		
		createModel();
		setFactories();
		socketChannel = createSocketChannel();
		createCommunicationObjects();
		addListeners();
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
//		createCommunicationObjects();
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
		System.out.println("Ready to send messages to server");
	}
	protected void createCommunicationObjects() {
		createSender();
	}
	
	protected void createSender() {
		meaningOfLifeSender = new AMeaningOfLifeClientSender(socketChannel,
				clientName);
	}
	protected void addListeners() {
		addModelListener();
	}
	protected void addModelListener(){
		meaningOfLifeModel.addPropertyChangeListener(meaningOfLifeSender);		
	}
	@Override
	public void notConnected(SocketChannel aSocketChannel, Exception e) {
		System.err.println("Could not connect:" +aSocketChannel);
		if (e != null)
		   e.printStackTrace();
	}
	/**
	 * Connect the client with the specified name to the specified server.
	 */
	public static void launchClient(String aServerHost, int aServerPort,
			String aClientName) {
		/*
		 * Put these two in your clients also
		 */
		FactoryTraceUtility.setTracing();
		BeanTraceUtility.setTracing();
		NIOTraceUtility.setTracing();
		MeaningOfLifeNIOClient aClient = new AMeaningOfLifeNIOClient(
				aClientName);
		aClient.initialize(aServerHost, aServerPort);		
	}



	public static void main(String[] args) {	
		launchClient(ClientArgsProcessor.getServerHost(args),
				ClientArgsProcessor.getServerPort(args),
				ClientArgsProcessor.getClientName(args));

	}
}
