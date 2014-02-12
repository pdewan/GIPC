package replicatedserverport.rpc.duplex.singleresponse;

import inputport.ConnectionListener;
import inputport.InputPort;
import inputport.datacomm.AnAbstractReceiveTrapper;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.simplex.SimplexClientInputPort;
import util.trace.Tracer;

//handles the connect and the server choice
public class AnUpstreamSingleResponseClientDuplexRelayedCallReceiveTrapper 
     extends AnAbstractSingleResponseClientDuplexCallReceiveTrapper
     implements ConnectionListener  {
//		SimplexClientInputPort clientInputPort;
//		ReplicatedServerDuplexClientPort clientInputPort;
		// shared with the downstream one
		// created by the upstreamdownstream factory
		// do so by creating a singleton?
		// or by creating a couple of related factories?
		// the later is more flexible	
//		ClientMessagesManager clientMessagesManager; 
													

	public AnUpstreamSingleResponseClientDuplexRelayedCallReceiveTrapper(InputPort anInputPort, 
				ReceiveNotifier<Object> destination,
				ClientMessagesManager aClientMessagesManager) {
		super(anInputPort, destination, aClientMessagesManager);
		clientInputPort.addConnectionListener(this);

		
		
	}
	
//	@Override
//	public synchronized void notConnected(String remoteEnd, String message) {
////		clientMessagesManager.removeServer(remoteEnd);
//	}
//	// forwards all messages. Does not try to figure out which server is the one it should get
//	// the return value from. The server itself will 
//	@Override
//	public  synchronized void notifyPortReceive(String remoteEnd, Object message) {
//		
//		destination.notifyPortReceive(remoteEnd, message);
//		
//		// message count chyanges  done by only the down stream one
//		
//		
//		
//		// if message is from a replicated server, then only update this
////		clientMessagesManager.receivedNewMessage();
////		if (clientMessagesManager.sendUpdateControlMessage()) {
////			processNewServer(clientMessagesManager.getCurrentServer());
////		}
//	}
//	void processNewServer(String remoteEnd) {
//		clientInputPort.setPhysicalRemoteEndPoint(remoteEnd);
//		sendControlMessage(remoteEnd);
////		ControlMessage controlMessage = new AControlMessage(remoteEnd, clientMessagesManager.getReceivedMessages());
////		Tracer.info(this, "Sending control message: " + controlMessage + " to " + remoteEnd);
////		clientMessagesManager.getSendTrapper().send(remoteEnd, controlMessage);
//	}
//	
//	void sendControlMessage(String remoteEnd) {
//		String currentServer = clientMessagesManager.getCurrentServer();
//		if (currentServer == null) return;
//		ControlMessage controlMessage = new AControlMessage(currentServer, clientMessagesManager.getReceivedMessages());
//		Tracer.info(this, "Sending control message: " + controlMessage + " to " + remoteEnd);
//		clientMessagesManager.getSendTrapper().send(remoteEnd, controlMessage);		
//	}
//	boolean controlMessageSent;  // this takes care of incremental
//	@Override
//	public synchronized void connected(String remoteEnd) {
//		if (remoteEnd.equals(clientInputPort.getLocalName())) 
//			return; // this is a false connect
//			
//		if (controlMessageSent) { // incremental
//			clientMessagesManager.addServer(remoteEnd);
//			String currentServer = clientMessagesManager.getCurrentServer();
//			sendControlMessage(currentServer);
//		} else
//		if (remoteEnd.equals(clientInputPort.getLogicalRemoteEndPoint())) {
//			// heard from all severs, now send the first control message
//			// in a more functional load balancing version, would use a static mapping to find
//			// the initial server and then try to randomize the selection of the next server if 
//			// failure occurs
//			// when this is attached to a non repliated server, getCurrentServer will return null and so this trapper has no effect
//			String currentServer = clientMessagesManager.getCurrentServer();
//			controlMessageSent = true;
//			sendControlMessage(currentServer);
//		} else {			
//		    boolean newServer = clientMessagesManager.addServer(remoteEnd);
//		    // do not chnage servers until you have heard from all servers
////		    if (newServer) {
////				String currentServer = clientMessagesManager.getCurrentServer();
////				sendControlMessage(currentServer);
////
////		    }
//		}
////		if (newServer)
////			processNewServer(clientMessagesManager.getCurrentServer());	
////		sendControlMessage(remoteEnd);
//	}
//	@Override
//	public synchronized void  disconnected(String remoteEnd, boolean explicitDsconnection,
//			String systemMessage) {
//		boolean newServer = clientMessagesManager.removeServer(remoteEnd);
//		if (newServer)
//			processNewServer(clientMessagesManager.getCurrentServer());		
//	}	

}
