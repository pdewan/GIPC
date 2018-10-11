package extraip;

import java.nio.ByteBuffer;

import inputport.datacomm.simplex.SimplexInputPortFactory;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.SimplexBufferInputPortSelector;
import inputport.datacomm.simplex.buffer.nio.AnNIOSimplexBufferInputPortFactory;




public class BufferServerInputPortLauncher {

	public static void launch (String[] args) {
		
		//This should be in Global somewhere
//		InputPortFactory<ByteBuffer> untypedInputPortFactory = new ASocketInputPortFactory();
		SimplexInputPortFactory<ByteBuffer> untypedInputPortFactory = new AnNIOSimplexBufferInputPortFactory();
		SimplexBufferInputPortSelector.setSimplexBufferInputPortFactory(untypedInputPortFactory);
		SimplexServerInputPort serverInputPort = SimplexBufferInputPortSelector.createServerSimplexInputPort("9090", "test server");
		serverInputPort.connect();
		APrintingReceiveAndSendListener echoingReceiveListener = new APrintingReceiveAndSendListener(serverInputPort);
		serverInputPort.addConnectionListener(echoingReceiveListener);
		serverInputPort.addReceiveListener(echoingReceiveListener);		
	}

}
