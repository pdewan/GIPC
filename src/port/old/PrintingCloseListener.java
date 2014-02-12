package port.old;

import inputport.ConnectionType;
import inputport.DisconnectListener;


public class PrintingCloseListener implements DisconnectListener{

	@Override
	public void disconnected(String clientName, boolean explicitClose, String systemMessage, ConnectionType aConnectionType) {
		System.out.println(clientName + " port closed, explicitClose " + explicitClose + " system message:" + systemMessage);
		//return null;
	}

}
