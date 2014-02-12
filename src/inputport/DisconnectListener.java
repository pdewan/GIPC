package inputport;
public interface DisconnectListener {	
	public void  disconnected(String aRemoteEndName, boolean anExplicitDsconnection, String anExplanation, ConnectionType aConnectionType);
}
