package sessionport.rpc.duplex.example;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.rpc.DirectedRPCProxyGenerator;
import port.old.Adder;
import sessionport.rpc.duplex.DuplexRPCSessionPort;

public class ACallingConnectListener implements ConnectionListener{
	DuplexRPCSessionPort sessionPort;
	public ACallingConnectListener(DuplexRPCSessionPort aSessionPort) {
		sessionPort = aSessionPort;
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		if (aConnectionType == ConnectionType.MEMBER_TO_SESSION ) {
		System.out.print("Connected as client to: " + remoteEnd);
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
