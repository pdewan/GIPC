package port.sessionserver.relay.late.mvc.example;

import inputport.ConnectionListenerWithPort;
import inputport.ConnectionType;
import inputport.datacomm.ReceiveListener;
import inputport.rpc.duplex.DuplexRPCClientInputPort;

import java.util.List;

import port.relay.mvc.example.GenericRelayingCollaborativeFrostyModel;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.mvc.example.ARelayerConnectionListener;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class ALatecomerRelayerConnectionListener extends ARelayerConnectionListener implements LatecomerRelayerConnectionListener {
	List<MessageWithSource> messageList;
	ReceiveListener sessionJoiner;
	public ALatecomerRelayerConnectionListener(
			ServerPortDescription aServerPortDescription, String aClientName,
			ConnectionListenerWithPort aConnectionListener, GenericRelayingCollaborativeFrostyModel aModel, 
			List<MessageWithSource> aMessageList, ReceiveListener aSessionJoiner) {
		super(aServerPortDescription, aClientName, aConnectionListener, aModel);
		messageList = aMessageList;
		sessionJoiner = aSessionJoiner;
	}
	public void initMessageList(List<MessageWithSource> aMessageList) {
		messageList = aMessageList;
	}
	// do not print a second connection message to relayer as it is also the session server
	protected void traceConnected(String aRemoteEnd, ConnectionType aConnectionType) {
	}
	// do not immediately add model as receive listener
	@Override
	protected void addModelAsReceiveListener() {		
	}
	
	@Override
	protected ConnectionListenerWithPort createMVCConnectionListener() {
		return new ALatecomerMVCServerConnectionListener((DuplexRPCClientInputPort) inputPort,  model, messageList, sessionJoiner);
	}
	
}
