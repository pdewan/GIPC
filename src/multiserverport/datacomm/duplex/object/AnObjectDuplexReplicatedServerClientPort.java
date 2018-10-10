package multiserverport.datacomm.duplex.object;

import java.nio.ByteBuffer;
import java.util.Set;

import inputport.datacomm.duplex.object.ADuplexObjectClientInputPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;


public class AnObjectDuplexReplicatedServerClientPort 
	extends ADuplexObjectClientInputPort 
	implements DuplexMultiServerClientPort<Object>{
	
	DuplexMultiServerClientPort<ByteBuffer> bufferPort;
	public AnObjectDuplexReplicatedServerClientPort(
			DuplexMultiServerClientPort<ByteBuffer> aBufferPort) {
		super(aBufferPort);
		bufferPort = aBufferPort;
	}

	@Override
	public String getSender() {
		return bufferPort.getSender();
	}

	@Override
	public Set<String> getConnections() {
		return bufferPort.getConnections();
	}

	@Override
	public void setSender(String newVal) {
		bufferPort.setSender(newVal);
		
	}

}
