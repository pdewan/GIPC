package sessionport.datacomm.group.object.flexible.example;

import inputport.ConnectionListener;
import inputport.ConnectionType;
import inputport.datacomm.duplex.DuplexInputPort;

public class ATracingSessionConnectionListener implements ConnectionListener{
	String sessionName;
	DuplexInputPort inputPort;
	public ATracingSessionConnectionListener(String aSessionName, DuplexInputPort anInputPort) {
		sessionName = aSessionName;
		inputPort = anInputPort;
	}
	
	protected boolean isLogical(String aRemoteEndName) {
		return aRemoteEndName.equals(inputPort.getLogicalRemoteEndPoint());
	}

	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		
		if (!isLogical(aRemoteEndName)) {
			System.out.println("(Physical)");
		}
		System.out.println(sessionName + "<-->" + aRemoteEndName);

	}

	@Override
	public void notConnected(String aRemoteEndName, String anExplanation, ConnectionType aConnectionType) {
		
	}

	@Override
	public void disconnected(String aRemoteEndName,
			boolean anExplicitDsconnection, String anExplanation, ConnectionType aConnectionType) {
		
	}

}
