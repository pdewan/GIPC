package examples.nio.manager;

import java.io.IOException;
import java.net.InetAddress;
import java.nio.channels.SocketChannel;

import trace.port.nio.NIOTraceUtility;
import inputport.nio.manager.AConnectCommandFactory;
import inputport.nio.manager.AnObservableNIOManager;
import inputport.nio.manager.ConnectCommandSelector;
import inputport.nio.manager.ObservableNIOManager;
import inputport.nio.manager.ObservableNIOManagerFactory;

public class AMeaningOfLifeNIOClient implements MeaningOfLifeNIOClient{
//	ObservableNIOManager nioManager;
	String clientName;
	MeaningOfLifeModel meaningOfLifeModel;
	MeaningOfLifeController meaningOfLifeController;
	MeaningOfLifeView meaningOfLifeView;
	MeaningOfLifeClientSender meaningOfLifeSender;
	SocketChannel socketChannel;
	public AMeaningOfLifeNIOClient(String aClientName) {
//		try {
		ConnectCommandSelector.setFactory(new AConnectCommandFactory());
//		nioManager = new AnObservableNIOManager();
		clientName = aClientName;
		
//		SocketChannel aSocketChannel = createSocketChannel();
//		InetAddress aServerAddress = InetAddress.getByName(aServerHost);
//		nioManager.connect(aSocketChannel, aServerAddress, aServerPort, this);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}		
	}
	
	public void createModel() {
		meaningOfLifeModel = new AMeaningOfLifeModel();
//		meaningOfLifeView = new AMeaningOfLifeView();
//		meaningOfLifeModel.addPropertyChangeListener(meaningOfLifeView);
//		meaningOfLifeController = new AMeaningOfLifeController(meaningOfLifeModel);
//		meaningOfLifeController.processInput();
	}
	public void createUI() {
//		meaningOfLifeModel = new AMeaningOfLifeModel();
		meaningOfLifeView = new AMeaningOfLifeView();
		meaningOfLifeModel.addPropertyChangeListener(meaningOfLifeView);
		meaningOfLifeController = new AMeaningOfLifeController(meaningOfLifeModel);
		meaningOfLifeController.processInput();
	}
	public void processInput() {
		meaningOfLifeController.processInput();
	}
	
	public void initiateConnection(String aServerHost, int aServerPort)  {
		try {
			
			socketChannel = createSocketChannel();
			InetAddress aServerAddress = InetAddress.getByName(aServerHost);
			ObservableNIOManagerFactory.getSingleton().connect(socketChannel, aServerAddress, aServerPort, this);
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
		meaningOfLifeSender = new AMeaningOfLifeClientSender(aSocketChannel, clientName);
		meaningOfLifeModel.addPropertyChangeListener(meaningOfLifeSender);
	}
	@Override
	public void notConnected(SocketChannel theSocketChannel, Exception e) {
		e.printStackTrace();
		
	}
	public static void createClient(String aServerHost, int aServerPort, String aClientName){
		MeaningOfLifeNIOClient aClient = new AMeaningOfLifeNIOClient(aClientName);
		aClient.createModel();
		aClient.initiateConnection(aServerHost, aServerPort);
		aClient.createUI();
	}
//	public static String chooseServerHost(String[] args){
//		return args.length == 1?args[0]:"localhost";
//	}
	public static void main (String[] args) {
		NIOTraceUtility.setTracing();
		createClient(
				ClientArgsProcessor.chooseServerHost(args), ServerPort.SERVER_PORT, 
				ClientArgsProcessor.chooseClientName(args) );	
		
	}
}
