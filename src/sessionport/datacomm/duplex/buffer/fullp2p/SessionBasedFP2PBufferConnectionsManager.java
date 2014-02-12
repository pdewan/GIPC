package sessionport.datacomm.duplex.buffer.fullp2p;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.sessionserver.SessionObserver;
import sessionport.datacomm.duplex.SessionInfo;
import staticsessionport.datacomm.duplex.buffer.fullp2p.BufferStaticSessionPortConnectionManagerFullP2P;


public interface SessionBasedFP2PBufferConnectionsManager  extends SessionObserver, 
		BufferStaticSessionPortConnectionManagerFullP2P, SessionInfo {


//	void joinSessionsServer(SessionsServer aSessionsServer, String aSessionName);
	void joinSessionsServer(DuplexRPCClientInputPort aClientInputPort, 
			String aSessionName);


//	void closeAllConnections();
//	DuplexRPCInputPort getSessionsServerInputPort();
//	Set<String> getRemoteEndPoints();
//	void send(String remoteName, Object message);


}
