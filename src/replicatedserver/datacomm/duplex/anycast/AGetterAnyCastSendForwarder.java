package replicatedserver.datacomm.duplex.anycast;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.datacomm.group.GroupSender;
import inputport.rpc.RemoteCall;
import port.common.DistMisc;
import replicatedserverport.datacomm.simplex.AnAbstractMultiToReplicatedSendTrapper;
import util.misc.RemoteReflectionUtility;
import util.trace.Tracer;


public class AGetterAnyCastSendForwarder<MessageType> extends AnAbstractMultiToReplicatedSendTrapper<MessageType, MessageType> 
	implements  ConnectionListener {
	List<String> connectedServers = new ArrayList();
	public AGetterAnyCastSendForwarder(InputPort anInputPort, GroupSender<MessageType> aDestination) {
		super(aDestination);		
	}	
	boolean isGetter(Object message) {
//		if (!(message instanceof SerializableCall)) return false;	
//		SerializableCall serializableCall = (SerializableCall) message;
//		Method method = serializableCall.getSerializableMethod().getMethod();
//		return ReflectionUtility.isGetter(method);		
		
		if (!(message instanceof RemoteCall)) return false;	
		RemoteCall serializableCall = (RemoteCall) message;
		Method method = serializableCall.getMethod();
		return RemoteReflectionUtility.isGetter(method);	
	}
	@Override
	public void send(String remoteName, MessageType message) {
		if (!isGetter(message)) destination.sendAll(message);
		int randomIndex = DistMisc.random(0, connectedServers.size());
		String randomDestination = connectedServers.get(randomIndex);
		Tracer.info("Picking server " + randomDestination + " at index " + randomIndex);
		destination.send(randomDestination, message);
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		connectedServers.add(remoteEnd);
	}

	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		connectedServers.remove(remoteEndName);
		
	}

	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		connectedServers.remove(remoteEnd);

	}

}
