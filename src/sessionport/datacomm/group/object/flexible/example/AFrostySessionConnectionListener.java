package sessionport.datacomm.group.object.flexible.example;

import inputport.ConnectionType;
import inputport.datacomm.duplex.DuplexInputPort;


public class AFrostySessionConnectionListener extends ATracingSessionConnectionListener {
	DuplexInputPort sender;
	boolean messageSent;
	public AFrostySessionConnectionListener (String aSessionName, DuplexInputPort aSender) {
		super(aSessionName, aSender);
		sender = aSender;
	}
	@Override
	public void connected(String aRemoteEndName, ConnectionType aConnectionType) {
		super.connected(aRemoteEndName, aConnectionType);
		if (aRemoteEndName.equals(sender.getLocalName()) // local member joined
			|| messageSent && isLogical(aRemoteEndName)	 // second connect means logical & physical are different and so we do not want logical connection to trigger a new messgae
		
		) 
			return;
		messageSent = true;
		sender.send(aRemoteEndName, "Words of Robert Frost Please!");
	}

	
}
