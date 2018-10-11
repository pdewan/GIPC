package multiserverport.datacomm.simplex.object;

import java.nio.ByteBuffer;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.object.ASimplexObjectClientInputPort;
import multiserverport.datacomm.simplex.SimplexMultiServerClientPort;

public class AnObjectMultiServerClientPort extends ASimplexObjectClientInputPort implements SimplexMultiServerClientPort<Object>{

	SimplexClientInputPort<ByteBuffer> bbMultiServerClientPort;

	public AnObjectMultiServerClientPort(SimplexMultiServerClientPort<ByteBuffer> aBBClientInputPort) {
		super(aBBClientInputPort);
		bbMultiServerClientPort = aBBClientInputPort;
	}
		
	
//	public void addDisconnectListener(DisconnectListener portCloseListener) {
//		bbClientInputPort.addDisconnectListener(portCloseListener);
//	}
//	public void removeDisconnectListener(DisconnectListener portCloseListener) {
//		bbClientInputPort
//				.removeDisconnectListener(portCloseListener);
//	}
	

//	@Override
//	public void send(String remoteName, Object object) {
//		send(object);
//		
//	}
//
//	@Override
//	public void send(Object message) {
//		if (message instanceof  ByteBuffer)
//			bbClientInputPort.send((ByteBuffer) message); 
//		else if (message instanceof Serializable) {
//			ByteBuffer bbMessage = bufferSerializationSupport.outputBufferFromObject((Serializable) message);		
//			bbClientInputPort.send(bbMessage);	
//		} else {
//			Message.error("Sent message is neithet a bytebuffer nor serializable");
//		}
//	}
	
	
	

}
