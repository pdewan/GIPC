package inputport.datacomm.simplex.object;


import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexInputPortFactory;
import inputport.datacomm.simplex.SimplexServerInputPort;
import inputport.datacomm.simplex.buffer.SimplexBufferInputPortSelector;

import java.nio.ByteBuffer;



public class AnObjectInputPortFactory implements SimplexInputPortFactory<Object> {
	public AnObjectInputPortFactory() {
		ObjectTranslatingIPTrapperSelector.getTrapperSelector().
		setSendTrapperFactory(new ASerializingForwarderFactory());
		ObjectTranslatingIPTrapperSelector.getTrapperSelector().
	setReceiveTrapperFactory(new ADeserializingForwarderFactory());
	}

	@Override
	public SimplexServerInputPort<Object> createSimplexServerInputPort(String theServerId,
			String theServerName) {
		SimplexServerInputPort<ByteBuffer> bbServerInputPort = SimplexBufferInputPortSelector.createServerSimplexInputPort(theServerId, theServerName);
		return  new ASimplexObjectServerInputPort(bbServerInputPort);


	
	}
	@Override
	public SimplexClientInputPort<Object> createSimplexClientInputPort(String theServerHost,
			String theServerId, String aServerName, String theClientName) {
		SimplexClientInputPort<ByteBuffer> bbClientInputPort = SimplexBufferInputPortSelector.createSimplexClientInputPort(theServerHost, theServerId, aServerName, theClientName);
		return new ASimplexObjectClientInputPort(bbClientInputPort);

	}
}
