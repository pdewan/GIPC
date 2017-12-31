package examples.nio.manager;

import inputport.nio.manager.NIOManager;
import inputport.nio.manager.NIOManagerFactory;

import java.beans.PropertyChangeEvent;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class AMeaningOfLifeClientSender implements MeaningOfLifeClientSender{
//	ObservableNIOManager nioManager;
	SocketChannel socketChannel;
	String clientName;
	public AMeaningOfLifeClientSender(SocketChannel aSocketChannel, String aClientName) {
//		nioManager = anNIOManager;
		socketChannel = aSocketChannel;	
		clientName = aClientName;
	}
	
//	@Override
//	public synchronized void run() {
//		while (true) {
//			System.out.println("Meaning of life?");
//			String aMeaning = scanner.nextLine();				
//			ByteBuffer aMeaningByteBuffer = ByteBuffer.wrap((clientName + ":" + aMeaning).getBytes());
//			nioManager.write(socketChannel, aMeaningByteBuffer);	
//			String aMessageSent = new String( aMeaningByteBuffer.array());
//		}		
//	}
//	public void createLocalUI() {
//		while (true) {
//			System.out.println("Meaning of life?");
//			String aMeaning = scanner.nextLine();				
//			ByteBuffer aMeaningByteBuffer = ByteBuffer.wrap((clientName + ":" + aMeaning).getBytes());
//			System.out.println("Meaning:" + aMeaning);
//			nioManager.write(socketChannel, aMeaningByteBuffer);	
//			String aMessageSent = new String( aMeaningByteBuffer.array());
//		}	
//		
//	}

	@Override
	public void propertyChange(PropertyChangeEvent anEvent) {
		if (!anEvent.getPropertyName().equals("Meaning")) return;
		ByteBuffer aMeaningByteBuffer = ByteBuffer.wrap((clientName + ":" + anEvent.getNewValue()).getBytes());
		NIOManagerFactory.getSingleton().write(socketChannel, aMeaningByteBuffer);	
	}

}
