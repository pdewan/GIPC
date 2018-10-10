package port.old;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexInputPortFactory;
import inputport.datacomm.duplex.buffer.DuplexBufferInputPortSelector;
import inputport.datacomm.duplex.buffer.nio.AnNIODuplexBufferInputPortFactory;



public class NIODuplexServerInputPortLauncher {
	public static void main (String[] args) {
		DuplexInputPortFactory<ByteBuffer> untypedInputPortFactory = new AnNIODuplexBufferInputPortFactory();
		DuplexBufferInputPortSelector.setDuplexBufferInputPortFactory(untypedInputPortFactory);
		BufferDuplexServerInputPortLauncher.launch(args);
	
	}

}
