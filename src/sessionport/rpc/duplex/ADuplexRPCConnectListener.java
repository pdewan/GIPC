package sessionport.rpc.duplex;

import java.util.HashSet;
import java.util.Set;

import inputport.ConnectionListener;
import inputport.ConnectionType;

public class ADuplexRPCConnectListener implements ConnectionListener{
	protected DuplexRPCSessionPort sessionPort;
	Set<String> members = new HashSet();
	public ADuplexRPCConnectListener(DuplexRPCSessionPort aSessionPort) {
		sessionPort = aSessionPort;
	}
	
	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		System.out.println (sessionPort.getLocalName() + "<->" + remoteEnd);
		members.add(remoteEnd);
		
	}

	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		System.out.println (sessionPort.getLocalName() + "!<->" + remoteEndName);
		members.remove(remoteEndName);

	}

	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		
	}

}
