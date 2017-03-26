package sessionport.rpc.duplex.relayed.example;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.rpc.DirectedRPCProxyGenerator;
import sessionport.rpc.duplex.DuplexRPCSessionPort;

public class ACallingConnectListener implements ConnectionListener{
	protected DuplexRPCSessionPort sessionPort;
	protected Adder adderProxy;
	public ACallingConnectListener(DuplexRPCSessionPort aSessionPort) {
		sessionPort = aSessionPort;
	}
	protected Adder createAdderProxy(String remoteEnd) {
		return (Adder) DirectedRPCProxyGenerator.generateRPCProxy(sessionPort, remoteEnd, Adder.class, null);
	}
	
	protected void processObjectSum(Object aSum) {
		System.out.println ("Object Sum:" + aSum);
	}

	@Override
	public void connected(String remoteEnd, ConnectionType aConnectionType) {
		if (aConnectionType == ConnectionType.MEMBER_TO_SESSION ) { // connected to self
		System.out.println("Connected as client to: " + remoteEnd);
//		Adder adderProxy = (Adder) DirectedRPCProxyGenerator.generateRPCProxy(sessionPort, remoteEnd, Adder.class, null);
//		adderProxy = (Adder) DirectedRPCProxyGenerator.generateRPCProxy(sessionPort, remoteEnd, Adder.class, null);
		adderProxy =  createAdderProxy(remoteEnd);

		adderProxy.printSum(5, 6);	
		Object retVal = adderProxy.objectSum(5, 6);
		processObjectSum(retVal);
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
