package sessionport.rpc.group;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.rpc.group.GroupRPCProxyGenerator;
import inputport.rpc.group.GroupRPCServerInputPort;
import sessionport.rpc.duplex.relayed.example.Adder;

public class AnOldGroupCallingConnectListener implements ConnectionListener{
	GroupRPCServerInputPort sessionPort;
	public AnOldGroupCallingConnectListener(GroupRPCServerInputPort aSessionPort) {
		sessionPort = aSessionPort;
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		System.out.print("Connected as client to: " + remoteEnd);
		Adder adderProxy = (Adder) GroupRPCProxyGenerator.generateAllRPCProxy(sessionPort,  Adder.class, null);
		adderProxy.printSum(5, 6);		
	}

	@Override
	public void disconnected(String remoteEndName,
			boolean explicitDsconnection, String systemMessage, ConnectionType aConnectionType) {
		
	}

	@Override
	public void notConnected(String remoteEnd, String message, ConnectionType aConnectionType) {
		
	}

}
