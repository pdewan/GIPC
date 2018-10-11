package multiserverport.datacomm.duplex.object;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.object.ADuplexObjectClientInputPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;







public class AnObjectDuplexMultiServerClientPort 
	extends ADuplexObjectClientInputPort implements DuplexMultiServerClientPort<Object>{

	
	public AnObjectDuplexMultiServerClientPort(DuplexMultiServerClientPort<ByteBuffer> aBBClientInputPort) {
		super(aBBClientInputPort);

	}



}
