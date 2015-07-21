package sessionport.rpc.duplex.relayed.example;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.rpc.DirectedRPCProxyGenerator;
import sessionport.rpc.duplex.DuplexRPCSessionPort;

public class ACallingConnectListener implements ConnectionListener{
	DuplexRPCSessionPort sessionPort;
	public ACallingConnectListener(DuplexRPCSessionPort aSessionPort) {
		sessionPort = aSessionPort;
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		if (aConnectionType == ConnectionType.MEMBER_TO_SESSION || 
				aConnectionType == null) { // connected to self
		System.out.println("Connected as client to: " + remoteEnd);
		Adder adderProxy = (Adder) DirectedRPCProxyGenerator.generateRPCProxy(sessionPort, remoteEnd, Adder.class, null);
		adderProxy.printSum(5, 6);		
		}
	}

	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		
	}

	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		
	}

}
