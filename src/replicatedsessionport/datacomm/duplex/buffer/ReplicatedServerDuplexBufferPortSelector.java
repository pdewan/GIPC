package replicatedsessionport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import replicatedsessionport.datacomm.duplex.ReplicatedSessionDuplexPortFactory;

public class ReplicatedServerDuplexBufferPortSelector {
	static ReplicatedSessionDuplexPortFactory<ByteBuffer>  factory = 
		new AReplicatedSessionDuplexBufferPortFactory();
	
	public static void setDuplexReplicatedSessionObjectClientInputPortFactory (ReplicatedSessionDuplexPortFactory<ByteBuffer>  newVal) {
		factory = newVal;
	}
	public static DuplexServerInputPort<ByteBuffer> createReplicatedSessionServerInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String anId,
			String aName) {
		return factory.createDuplexReplicatedSessionServerInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, anId, aName, null);
		
	}
	
	public static DuplexClientInputPort<ByteBuffer> createDuplexReplicatedSessionClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName) {
		return factory.createDuplexReplicatedSessionClientInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName, null);
	}
	

}
