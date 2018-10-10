package inputport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexInputPortFactory;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.nio.AnNIOSimplexBufferInputPortFactory;



public class SimplexBufferInputPortSelector  {
	static SimplexInputPortFactory<ByteBuffer> untypedInputPortFactory = new AnNIOSimplexBufferInputPortFactory();
//	static SimplexInputPortFactory<ByteBuffer> untypedInputPortFactory = new ASocketInputPortFactory();
	public static void setSimplexBufferInputPortFactory(SimplexInputPortFactory<ByteBuffer> theInputPortFactory) {
		untypedInputPortFactory = theInputPortFactory;
	}
	public static SimplexInputPortFactory<ByteBuffer> getSimplexBufferInputPortFactory() {
		return untypedInputPortFactory;
	}
	public static SimplexServerInputPort<ByteBuffer> createServerSimplexInputPort(String theServerId, String theServerName) {
		return untypedInputPortFactory.createSimplexServerInputPort(theServerId, theServerName);
	}
	public static SimplexClientInputPort<ByteBuffer> createSimplexClientInputPort(String aHost, String aServerId, String aServerName, String aClientName){
		return untypedInputPortFactory.createSimplexClientInputPort(aHost, aServerId, aServerName, aClientName);
	}
}

