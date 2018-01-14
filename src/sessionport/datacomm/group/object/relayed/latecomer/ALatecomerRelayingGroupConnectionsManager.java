package sessionport.datacomm.group.object.relayed.latecomer;

import inputport.ConnectionType;
import inputport.rpc.duplex.RPCReturnValue;

import java.util.Collection;
import java.util.List;
import java.util.Set;

import port.ParticipantChoice;
import port.sessionserver.AJoinInfo;
import port.sessionserver.SessionParticipantDescription;
import port.sessionserver.relay.late.ALatecomerRelayer;
import port.sessionserver.relay.late.ALatecomerSessionServer;
import port.sessionserver.relay.late.LatecomerJoinInfo;
import port.sessionserver.relay.late.LatecomerRelayer;
import port.sessionserver.relay.late.LatecomerSessionServer;
import sessionport.datacomm.duplex.buffer.fullp2p.ASessionBasedFP2PBufferConnectionsManager;
import sessionport.datacomm.duplex.object.relayed.AMessageWithSource;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.datacomm.group.object.relayed.ARelayingGroupConnectionsManager;
import sessionport.datacomm.group.object.relayed.RelayingGroupConnectionsManager;
import util.trace.TraceableBus;
import util.trace.TraceableListener;
import util.trace.Tracer;
import util.trace.port.AsyncReplayEndInfo;
import util.trace.port.ReplayStartInfo;
import util.trace.port.SyncReplayEndInfo;
import util.trace.port.rpc.CallInitiated;
import util.trace.port.rpc.ReceivedCallEndedOld;
import util.trace.port.rpc.RemoteCallBlockedForReturnValue;



public class ALatecomerRelayingGroupConnectionsManager
	extends ARelayingGroupConnectionsManager 
	implements RelayingGroupConnectionsManager, TraceableListener {	
	LatecomerSessionServer logicalLatecomerSessionsServerProxy;
	protected boolean replayMode = true; // initially it will be true
	protected boolean asyncReplayEnded;
	AsyncReplayEndInfo asyncReplayEndInfo;
	
	protected int outstandingCallsInReplayMode;
	// caching proxy does this now
//	int numOutstandingFunctionCallsInReplayMode = 0;
	public ALatecomerRelayingGroupConnectionsManager(GroupSessionPort<Object> aGroupSessionPort, ParticipantChoice aChoice) {
		super (aGroupSessionPort, aChoice);
		TraceableBus.addTraceableListener(this);
//		Tracer.showInfo(true);
//		Tracer.setKeywordPrintStatus(this, true);

	}
	@Override
	protected void setSessionServerProxy() {		
//		latecomerSessionsServerProxy = (LatecomerSessionsServer) UniRPCProxyGenerator.generateUniRPCProxy(sessionsServerClientPort, 
//				null,
//				LatecomerSessionsServer.class, null);
//		sessionsServerProxy = latecomerSessionsServerProxy;
		super.setSessionServerProxy();
		logicalLatecomerSessionsServerProxy = (LatecomerSessionServer) logicalSessionsServerProxy;
		
	}	
	@Override
	protected void joinSessionServer() {
		doJoinSessionServer();
//		Thread thread = new Thread (this);
//		thread.setName("Latecomer relaying group session manager");
//		thread.start();
//		LatecomerJoinInfo joinRetVal = logicalLatecomerSessionsServerProxy.lateJoin(sessionName, sessionClientDescription, this);
////		LatecomerSessionsServer physicalSessionsServerProxy = (LatecomerSessionsServer) physicalServerNameToProxy.get(serverName);
////		LatecomerJoinInfo joinRetVal = physicalSessionsServerProxy.lateJoin(sessionName, sessionClientDescription, this);
//		List<ServerPortDescription> currentMembers = joinRetVal.getUsers();
//		processCurrentMembers(currentMembers);
//		List<MessageWithSource> pastMessages = joinRetVal.getMessages();
//		processPastMessages(pastMessages);
	}
	
//	// no reason for this to be a separate method as far as I can tell
//	protected void doJoinSessionServer() {
//		Tracer.info (this, "About to make late join session sync call to  session server :" + sessionName);
//		LatecomerJoinInfo joinRetVal = logicalLatecomerSessionsServerProxy.lateJoin(sessionName, sessionClientDescription, this);
////		LatecomerSessionsServer physicalSessionsServerProxy = (LatecomerSessionsServer) physicalServerNameToProxy.get(serverName);
////		LatecomerJoinInfo joinRetVal = physicalSessionsServerProxy.lateJoin(sessionName, sessionClientDescription, this);
//		Tracer.info (this, "Received back late join info :" + joinRetVal);
//		List<ServerPortDescription> currentMembers = joinRetVal.getUsers();
//		processCurrentMembers(currentMembers);
//		List<MessageWithSource> pastMessages = joinRetVal.getMessages();
//		processPastMessages(pastMessages);
//	}
	
	// no reason for this to be a separate method as far as I can tell
	// artiact of the fact that at one time remote calls were not executed
	// in a separate thread
	
	// actually received calls are in a separate thread
	// this is an initiated call, so needs gto be in separate thread
	protected void doJoinSessionServer() {
		List<SessionParticipantDescription> currentMembers = null;
		List<MessageWithSource> pastMessages = null;
		Tracer.info (this, "About to make late join session sync call to  session server :" + sessionName);
		LatecomerJoinInfo lateJoinInfo = null;
		switch (joinChoice) {
		case SYMMETRIC_JOIN:
		case MEMBER:
			
			lateJoinInfo = logicalLatecomerSessionsServerProxy.lateJoin(sessionName, sessionClientDescription, this);
			Tracer.info (this, "Received back late join info :" + lateJoinInfo);
//			currentMembers = AJoinInfo.getMembersClientsAndServers(lateJoinInfo);
			pastMessages = lateJoinInfo.getMessages(); //this was commented out before
			break;
		case SERVER_ONLY:
			lateJoinInfo = logicalLatecomerSessionsServerProxy.lateJoinAsServer(sessionName, sessionClientDescription, this);
//			currentMembers = AJoinInfo.getMembersAndClients(lateJoinInfo); // can add everyone if needed, connect type allows that
			pastMessages = lateJoinInfo.getClientMessages(); // commentd out before
			break;
		case CLIENT_ONLY:
			lateJoinInfo = logicalLatecomerSessionsServerProxy.lateJoinAsClient(sessionName, sessionClientDescription, this);
//			currentMembers =AJoinInfo.getMembersAndServers(lateJoinInfo); // can add everyone if necessary
			pastMessages = lateJoinInfo.getServerMessages(); //c commented out befoere
//			System.out.println("Latec join finished in client");
//			Tracer.setKeywordDisplayStatus(Tracer.ALL_KEYWORDS, true);
			break;
		}
		// this is members and servers in old distTeaching. This is what causes the connected call to have to
		// check if the joined process is  a server
		currentMembers = AJoinInfo.getMembersClientsAndServers(lateJoinInfo);
		// commenting this out as we want specialized semantics
//		pastMessages = lateJoinInfo.getMessages();
		servers = lateJoinInfo.getServers();
		clients = lateJoinInfo.getClients();
		members = lateJoinInfo.getMembers();
		

		// this is to be consistent with P2P, but perhaps this connection should not be generated
		// as it causes issues down the line with single response		
		// this connect will trigger addition of server
		duplexObjectSessionPort.notifyConnect(duplexObjectSessionPort.getLocalName(), 
		ASessionBasedFP2PBufferConnectionsManager.toMyConnectionType(joinChoice)); 

		processCurrentMembers(currentMembers);
		Tracer.info(this, "Past messages:" + pastMessages);
		processPastMessages(pastMessages); // bring this here so that control message (because of upstream connect) can be sent after messages processed
										  // this may cause connection failure
		
		// this is the connect that will trigget a control message
		// this happens after replay, so messages may not be sent
		if (duplexObjectSessionPort.getLogicalRemoteEndPoint() != null && currentMembers.size() > 0) { // a replicated port
        	duplexObjectSessionPort.notifyConnect(duplexObjectSessionPort.getLogicalRemoteEndPoint(), ConnectionType.TO_LOGICAL_SERVER);
        	initialJoin = false;
        	logicalConnectionNotificationSent = true;
		}
		
//		processPastMessages(pastMessages);
	}
	
//	public void run () {
//		doJoinSessionServer();
//	}
	
	
	// need to worry about return values to rpc calls
	// they should not be sent back
	// must put the system in a special mode where these values
	// are not sent	
	// need to synchronize this so that new messages and processed later
	// bu then return values will not be trapped as they are generated
	// maybe we count the number and then
	// do not send back those number of them
	// need to synchronize the receive call also
	// could also not send calls with return values
	// the only issue was that the server and count wsa not updating atomically
	// leading to server not thinking it was a latecomer and sending spurious messages
	protected  synchronized void processPastMessages(List<MessageWithSource> aMessageList) {
		if (aMessageList == null || aMessageList.size() == 0) { // test size to prevent spurious events
			replayModeEnded();
			return;
		}
//		System.out.println("Procsss past messages");
		ReplayStartInfo.newCase(this, aMessageList);
//		System.out.println("after new case in past messages");

		for (int i=0; i<aMessageList.size(); i++) {			
			MessageWithSource nextMessage = aMessageList.get(i);
			if ((joinChoice != ParticipantChoice.SERVER_ONLY) && (nextMessage.getMessage() instanceof RPCReturnValue )) {
			// we did not make a call so what are we doing with an RPC return value if a we are a member
			// we want to process return value only if this is a server_only and it is getting a return value
			// to a call made by a replicated peer
			continue;
		}
			
				
//			Tracer.info(this, "Playing past message: " + nextMessage);
			// assuming the only reason for server_only is to be replicated in which case we do log. Another alernative
//			if (joinChoice != ParticipantChoice.SERVER_ONLY && FunctionReturnValueDeterminerSelector.isFunctionCall(nextMessage.getMessage()))
//				numOutstandingFunctionCallsInReplayMode++;
			// could just send the messagwithsource with arbitrary source, but that makes it look odd
			// need to serialize the processing of this message. Currently
			// if the message is a serializable call, it executes in a separate thread
			// which can access the client manager at the same time as this thread
			// so basically, this thread could finish while the previos thread is still 
			// working and then process a control message that executes in this thread.
			if (nextMessage.getMessage() instanceof RPCReturnValue) // make sure the requests of them do not block as blokcing indicates end of replay
				processPastMessage(nextMessage.getSource(), nextMessage.getMessage());

//			messageReceived(nextMessage.getSource(), nextMessage.getMessage());
			
		}
		for (int i=0; i<aMessageList.size(); i++) {			
			MessageWithSource nextMessage = aMessageList.get(i);
//			if ((joinChoice != ParticipantChoice.SERVER_ONLY) && (nextMessage.getMessage() instanceof SerializableCall )) {
//				// will assume only servers have calls made on them, at least initiated on them. Mmebers send data to each other
//				continue;
//			}
			
			
//			Tracer.info(this, "Playing past message: " + nextMessage);
			// assuming the only reason for server_only is to be replicated in which case we do log. Another alernative
//			if (joinChoice != ParticipantChoice.SERVER_ONLY && FunctionReturnValueDeterminerSelector.isFunctionCall(nextMessage.getMessage()))
//				numOutstandingFunctionCallsInReplayMode++;
			// could just send the messagwithsource with arbitrary source, but that makes it look odd
			// need to serialize the processing of this message. Currently
			// if the message is a serializable call, it executes in a separate thread
			// which can access the client manager at the same time as this thread
			// so basically, this thread could finish while the previos thread is still 
			// working and then process a control message that executes in this thread.
			if (! (nextMessage.getMessage() instanceof RPCReturnValue)) // now play other messages

			processPastMessage(nextMessage.getSource(), nextMessage.getMessage());

//			messageReceived(nextMessage.getSource(), nextMessage.getMessage());
			
		}
		AsyncReplayEndInfo.newCase(this, aMessageList);

	}
//	Object lastMessageWaitedFor= null;
	// will be overrident by single response connection manager
	protected void processPastMessage (String remoteClientName,
			Object message) {
//		System.out.println("Playing past message:" + message);

		Tracer.info(this, "Playing past message:" + message);
		// should not make serializable call
		if ( !(message instanceof RPCReturnValue) && outstandingCallsInReplayMode > 0) {

//		if (joinChoice == ParticipantChoice.SERVER_ONLY && !(message instanceof RPCReturnValue) && outstandingCallsInReplayMode > 0) {
//			lastMessageWaitedFor = message;
			Tracer.info (this, "Waiting to replay message:" + message + " as previous call not finished");
			doWait();
			
		}
		messageReceived(remoteClientName, message);
//		if (message instanceof RemoteCall)
//			doWait(); // let us make sure the history is correctly replayed

	}
	
	// here we need to worrry abou responses
	// cannot just see what responses come during replay as the received calls
	// are executed in a separate thread. Id send changes state, we should
	// make it synchronized
	// for similar reasons we cannot change a global mode variable as there is
	// asynchrony
	// can make recevers of relayed messages latecomer aware. it has problems also
	// so we will simply make this module rpc aware
	// if client is doing its onw protocol for rpc, responses will be sent
	
	@Override
	public void send(String remoteEnd, Object message) {
//		// in replay mode, no one waiting for return value
//		if (numOutstandingFunctionCallsInReplayMode > 0 && 
//				FunctionReturnValueDeterminerSelector.isReturnValue(message)) {
//			numOutstandingFunctionCallsInReplayMode--;
//			return;
//		}
//		if (!replayMode)
		if (preSend(message))
		super.send(remoteEnd, message);

	}
	protected boolean preSend(Object message) {
		return !replayMode;
	}
	@Override
	public void send(Collection<String> clientNames, Object message) {
//		if (!replayMode) // control message is also not sent!
		if (preSend(message))

//		super.send(clientNames, message);
			((LatecomerRelayer) relayerProxy).relay(sessionName, clientNames, new AMessageWithSource(groupObjectSessionPort.getLocalName(), message));	
//		relayerProxy.relay(clientNames, new AMessageWithSource(groupObjectSessionPort.getLocalName(), message));	


	}

	@Override
	public void sendOthers(Object message) {
//		if (!replayMode)
		if (preSend(message))

		super.sendOthers(message);
		
	}
	@Override
	protected void getAndConnectToRelayer() {	
		Tracer.info(this, "Making relayer port the same as sessions server port: " + sessionServerClientPort);
		relayerClientInputPort = sessionServerClientPort;
//		setRelayerProxy(); // the connect call may trigger a control message
		connected(relayerClientInputPort.getLogicalRemoteEndPoint(), ConnectionType.TO_SERVER);
	}
	@Override
	// since we do not have to make an RPC call in getAndConnectToRelayer, we can do this synchronously
	protected void maybeAsyncSetUpRelayer() {
		setUpRelayer(); 
	}
	@Override
	protected Class getRelayerType() {
		return ALatecomerRelayer.class;
	}
	@Override
	protected Class getSessionsServerType() {
		return ALatecomerSessionServer.class;
	}
	
	protected synchronized void doWait() {
		try {
			wait();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	synchronized void doNotify() {
		try {
			notify();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// incase some action has to be taken as in subclass

    protected synchronized void replayModeEnded() {
    	Tracer.info(this, "Replay mode ended");
    	replayMode = false;
    	outstandingCallsInReplayMode = 0;
		
	}
	
	@Override
	public synchronized  void newEvent(Exception aTraceable) {
//			System.out.println ("Traceable:" + aTraceable);
			// actually should see if the message was really sent or not, and not just delayed or buffered - or should one?
			// if delayed, the toString message will also be delayed
//			if (aTraceable instanceof ByteBufferSendInfo || aTraceable instanceof ByteBufferReceiveInfo)
			if (aTraceable instanceof ReplayStartInfo ) { // event generated and consumed
				Tracer.info(this, "Enter replay mode");
				replayMode = true;
				asyncReplayEnded = false;				
//				replayModeStarted();
			} else if (aTraceable instanceof SyncReplayEndInfo) { // event generated and consumed
				// need these two steps to be done atomically
//				replayMode = false; 
				replayModeEnded();
				
			} else if ( aTraceable instanceof CallInitiated) {  // can this be call received?
				if (replayMode) {
					outstandingCallsInReplayMode++;
					Tracer.info(this, "Incremented outstandingCallsInReplayMode to: " + outstandingCallsInReplayMode);
					// let us just allow multiple RPC values per source
//					doWait(); // we get the exact history
					

				}

			} else if (aTraceable instanceof ReceivedCallEndedOld ||
					// only last call will block as return values processed first
					(aTraceable instanceof RemoteCallBlockedForReturnValue )) // last call  replay has ended, need to get out of replay mode to receive new messages
				{ 
				if (replayMode) {
					if (outstandingCallsInReplayMode > 0)
						outstandingCallsInReplayMode--;
					else
						return; // the call to the session server (which does not increment the counter) can block and result in this path being taken
					Tracer.info(this, "Decremented outstandingCallsInReplayMode to: " + outstandingCallsInReplayMode);
					doNotify();
					maybeSendSyncReplayEndInfo();
//					if 	(outstandingCallsInReplayMode == 0) {
////						AsyncReplayEndInfo asyncReplayEndInfo = (AsyncReplayEndInfo) aTraceable;
////						AsyncReplayEndInfo asyncReplayEndInfo = (AsyncReplayEndInfo) aTraceable;
//
//						SyncReplayEndInfo.newCase(asyncReplayEndInfo.getFinder(), asyncReplayEndInfo.getReplayMessageList());
//					}

				}
			} 
			else if (aTraceable instanceof AsyncReplayEndInfo) {
				// this does not work because the replay happens 
				// asynchronously
//				replayMode = false; 
				Tracer.info(this, "Async replay ended, there may be one outstanding call");
				asyncReplayEndInfo = (AsyncReplayEndInfo) aTraceable;
				asyncReplayEnded = true;
				maybeSendSyncReplayEndInfo();
//				if 	(outstandingCallsInReplayMode == 0) {
////					 asyncReplayEndInfo = (AsyncReplayEndInfo) aTraceable;
//					SyncReplayEndInfo.newCase(asyncReplayEndInfo.getFinder(), asyncReplayEndInfo.getReplayMessageList());
//				}


			} 
			
		}
			
		void maybeSendSyncReplayEndInfo() {
			Tracer.info(this, "NUm outstanding calls: " + outstandingCallsInReplayMode + " async replay ended:" + asyncReplayEnded);
			if 	(outstandingCallsInReplayMode == 0 && asyncReplayEnded) {
//				 asyncReplayEndInfo = (AsyncReplayEndInfo) aTraceable;
				SyncReplayEndInfo.newCase(asyncReplayEndInfo.getFinder(), asyncReplayEndInfo.getReplayMessageList());
			}
		}
	

}
