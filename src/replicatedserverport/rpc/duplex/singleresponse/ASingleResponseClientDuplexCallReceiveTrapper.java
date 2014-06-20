package replicatedserverport.rpc.duplex.singleresponse;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.simplex.SimplexClientInputPort;
import util.trace.Tracer;
/*
does not just forwards also generates
 usually this trapper is upstream from ABufferDuplexMultiServerClientPort and gets only
 connections to statis servers. However when we are dealling with both replciated session port and server
 then the connect method is also called with replicated servers and other session participants registered
 with a (possibly replicated) session manager. In that case it is upstream of the session manager connection manager.
 I thought I had a different downstream and upstream receive trapper but classes with these names excist but it does not seem 
 they are references. So this traper is used for both replicated servers. It does not currenty distinguish between
 serer participants and others, so it makes for instance alice a server for bob,
 though bob is not ready to get control messages from alice.
 So I am putting in code to distinguish between them in the connected method
 */
public class ASingleResponseClientDuplexCallReceiveTrapper 
               extends AnAbstractReceiveTrapper<Object, Object> implements ConnectionListener  {
		SimplexClientInputPort clientInputPort;
//		ReplicatedServerDuplexClientPort clientInputPort;
		ClientMessagesManager clientMessagesManager;

	public ASingleResponseClientDuplexCallReceiveTrapper(InputPort anInputPort, 
				ReceiveNotifier<Object> destination,
				ClientMessagesManager aClientMessagesManager) {
		super(null, destination);
		clientInputPort = (SimplexClientInputPort) anInputPort;
		clientInputPort.addConnectionListener(this);
		clientMessagesManager = aClientMessagesManager;
	}

	@Override
	public synchronized void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
//		clientMessagesManager.removeServer(remoteEnd);
	}
	// forwards all messages. Does not try to figure out which server is the one it should get
	// the return value from. The server itself will 
	@Override
	public  synchronized void notifyPortReceive(String remoteEnd, Object message) {
		
		destination.notifyPortReceive(remoteEnd, message);
		// if message is from a replicated server, then only update this
		clientMessagesManager.receivedNewMessage(remoteEnd, message);
		if (clientMessagesManager.sendUpdateControlMessage()) {
			processNewServer(clientMessagesManager.getCurrentServer());
		}
	}
	void processNewServer(String remoteEnd) {
		clientInputPort.setPhysicalRemoteEndPoint(remoteEnd);
		sendControlMessage(remoteEnd);
//		ControlMessage controlMessage = new AControlMessage(remoteEnd, clientMessagesManager.getReceivedMessages());
//		Tracer.info(this, "Sending control message: " + controlMessage + " to " + remoteEnd);
//		clientMessagesManager.getSendTrapper().send(remoteEnd, controlMessage);
	}
	
	void sendControlMessage(String remoteEnd) {
		String currentServer = clientMessagesManager.getCurrentServer();
		if (currentServer == null) return;
		ControlMessage controlMessage = new AControlMessage(currentServer, clientMessagesManager.getReceivedMessages());
		Tracer.info(this, "Sending control message: " + controlMessage + " to " + remoteEnd);
		clientMessagesManager.getSendTrapper().send(remoteEnd, controlMessage);		
	}
	boolean controlMessageSent;  // this takes care of
	
	@Override
	public synchronized void connected(String remoteEnd, ConnectionType aConnectionType) {
		if (remoteEnd.equals(clientInputPort.getLocalName())) 
			return; // this is a false connect
		// this check has been added because connect calls are made to clients also
		if (aConnectionType == ConnectionType.CLIENT_TO_SESSION )
//				|| 
//				aConnectionType == ConnectionType.MEMBER_TO_SESSION )	// strictly speaking a member is a server but I should not consider one so
			return; // when this is used for replciated servers registered with sessiom manager
		if (controlMessageSent) { // incremental
			clientMessagesManager.addServer(remoteEnd);
			String currentServer = clientMessagesManager.getCurrentServer();
			sendControlMessage(currentServer);
		} else
		if (remoteEnd.equals(clientInputPort.getLogicalRemoteEndPoint())) {
			// heard from all severs, now send the first control message
			// in a more functional load balancing version, would use a static mapping to find
			// the initial server and then try to randomize the selection of the next server if 
			// failure occurs
			// when this is attached to a non repliated server, getCurrentServer will return null and so this trapper has no effect
			String currentServer = clientMessagesManager.getCurrentServer();
			controlMessageSent = true;
			sendControlMessage(currentServer);
		} else {			
		    boolean newServer = clientMessagesManager.addServer(remoteEnd);
		    // do not chnage servers until you have heard from all servers
//		    if (newServer) {
//				String currentServer = clientMessagesManager.getCurrentServer();
//				sendControlMessage(currentServer);
//
//		    }
		}
//		if (newServer)
//			processNewServer(clientMessagesManager.getCurrentServer());	
//		sendControlMessage(remoteEnd);
	}
	@Override
	public synchronized void  disconnected(String remoteEnd, boolean explicitDsconnection,
			String systemMessage, ConnectionType aConnectionType) {
		boolean newServer = clientMessagesManager.removeServer(remoteEnd);
		if (newServer)
			processNewServer(clientMessagesManager.getCurrentServer());		
	}	

}
