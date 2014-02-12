package port.sessionserver;

import inputport.rpc.duplex.DuplexRPCServerInputPort;

public interface LocalSessionsServer extends SessionServer{
	public Session getAndMaybeCreateSession(String aSessionName);

	void setDuplexRPCServerInputPort(
			DuplexRPCServerInputPort duplexRPCServerInputPort);

	DuplexRPCServerInputPort getDuplexRPCServerInputPort();

}
