package replicatedsessionport.datacomm.simplex.buffer;

import inputport.datacomm.simplex.SimplexClientInputPort;
import inputport.datacomm.simplex.SimplexServerInputPort;

import java.nio.ByteBuffer;

import port.ParticipantChoice;

import replicatedsessionport.datacomm.simplex.ReplicatedSessionPortFactory;

public class ReplicatedServerBufferClientPortSelector {
	static ReplicatedSessionPortFactory<ByteBuffer>  factory = 
		new AReplicatedSessionBufferPortFactory();
	
	public static void setReplicatedSessionObjectClientInputPortFactory (ReplicatedSessionPortFactory<ByteBuffer>  newVal) {
		factory = newVal;
	}
	
	public static SimplexClientInputPort<ByteBuffer> createReplicatedSessionClientInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName) {
		return factory.createReplicatedSessionClientInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName);
	}
	public SimplexServerInputPort<ByteBuffer> createReplicatedSessionServerInputPort(String aSessionsServerHost, 
			String aSessionsServerId, 
			String aSessionsServerName, 
			String aSessionName, 
			String aLogicalRemoteEndPoint,
			String aName) {
		return factory.createReplicatedSessionServerInputPort(aSessionsServerHost, aSessionsServerId, aSessionsServerName, aSessionName, aLogicalRemoteEndPoint, aName);
	}
	

}
