package port.old;

import extraip.DisconnectRegistrarAndNotifier;

public class ADisconnectNotifyCommand implements Command {
	DisconnectRegistrarAndNotifier disconnectNotifier;
	String remoteEnd;
	boolean eof;
	String closeReason;
	public ADisconnectNotifyCommand(DisconnectRegistrarAndNotifier theConnectionNotifier, 
			String theRemoteEnd, boolean theEof, String theCloseReason) {
		disconnectNotifier = theConnectionNotifier;
		remoteEnd = theRemoteEnd;		
	}
	@Override
	public void execute() {
		disconnectNotifier.notifyDisconnect(remoteEnd, eof, closeReason, null);
	}
}
