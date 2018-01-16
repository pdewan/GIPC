package examples.nio.manager.client;

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

	@Override
	public void propertyChange(PropertyChangeEvent anEvent) {
		if (!anEvent.getPropertyName().equals("Meaning")) return;
		ByteBuffer aMeaningByteBuffer = ByteBuffer.wrap((clientName + ":" + anEvent.getNewValue()).getBytes());
		NIOManagerFactory.getSingleton().write(socketChannel, aMeaningByteBuffer);	
	}

}
