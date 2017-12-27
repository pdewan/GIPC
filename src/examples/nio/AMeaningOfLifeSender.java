package examples.nio;

import inputport.nio.ObservableNIOManager;

import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

public class AMeaningOfLifeSender implements Runnable{
	Scanner scanner = new Scanner (System.in);
	ObservableNIOManager nioManager;
	SocketChannel socketChannel;
	String clientName;
	public AMeaningOfLifeSender(ObservableNIOManager anNIOManager, SocketChannel aSocketChannel, String aClientName) {
		nioManager = anNIOManager;
		socketChannel = aSocketChannel;	
		clientName = aClientName;
	}
	@Override
	public synchronized void run() {
		while (true) {
			System.out.println("Meaning of life?");
			String aMeaning = scanner.nextLine();				
			ByteBuffer aMeaningByteBuffer = ByteBuffer.wrap((clientName + ":" + aMeaning).getBytes());
			nioManager.write(socketChannel, aMeaningByteBuffer);	
			String aMessageSent = new String( aMeaningByteBuffer.array());
		}		
	}

}
