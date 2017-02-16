package port;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.InputPort;




public class ATracingConnectionListener implements  ConnectionListener {
	protected InputPort inputPort;
	public ATracingConnectionListener(InputPort anInputPort) {
		inputPort = anInputPort;		
	}
	public ATracingConnectionListener() {
	}
	@Override
	public void disconnected(String aRemoteEnd, boolean anExplicitClose, String aSystemMessage, ConnectionType aConnectionType) {
		traceDisconnected(aRemoteEnd, anExplicitClose, aSystemMessage, aConnectionType);
//		System.out.println(inputPort.getLocalName() + "<-->" + aRemoteEnd + " (Closed)");
//		if (anExplicitClose) {
//			System.out.println("Explanation: Explicit disconnect");
//		} else {
//			System.out.println("Explanation: " + aSystemMessage);
//		}
	}
	protected void traceDisconnected(String aRemoteEnd, boolean anExplicitClose, String aSystemMessage, ConnectionType aConnectionType) {
		if (inputPort != null)
		System.out.println(inputPort.getLocalName() + "<-->" + aRemoteEnd + " (Closed)");
		else
			System.out.println(aRemoteEnd + " (Closed)");
		if (anExplicitClose) {
			System.out.println("Explanation: Explicit disconnect");
		} else {
			System.out.println("Explanation: " + aSystemMessage);
		}
	}
	@Override
	public void connected(String aRemoteEnd, ConnectionType aConnectionType) {
		traceConnected(aRemoteEnd, aConnectionType);
//		System.out.println(inputPort.getLocalName() + "<-->" + aRemoteEnd + " (Opened)");		
	}
	protected void traceConnected(String aRemoteEnd, ConnectionType aConnectionType) {
		if (inputPort != null)
		System.out.println(inputPort.getLocalName() + "<-->" + aRemoteEnd + " (Opened)");
		else
			System.out.println( aRemoteEnd + " (Opened)");	
	}
	
	@Override
	public void notConnected(String aRemoteEnd, String aMessage, ConnectionType aConnectionType) {
		traceNotConnected(aRemoteEnd, aMessage, aConnectionType);
	}
	public void traceNotConnected(String aRemoteEnd, String aMessage, ConnectionType aConnectionType) {
		if (inputPort != null)
		System.out.println(inputPort.getLocalName() + "<-->" + aRemoteEnd + " (Could not connect because: " + aMessage + " )");
		else
			System.out.println( aRemoteEnd + " (Could not connect because: " + aMessage + " )");

		
	}
}
