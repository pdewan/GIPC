package port.old;


public class AConnectNotifyCommand implements Command {
	PureConnectRegistrarAndNotifier connectionNotifier;
	String remoteEnd;
	public AConnectNotifyCommand(PureConnectRegistrarAndNotifier theConnectionNotifier, String theRemoteEnd) {
		connectionNotifier = theConnectionNotifier;
		remoteEnd = theRemoteEnd;		
	}
	@Override
	public void execute() {
		connectionNotifier.notifyConnect(remoteEnd, null);
	}

}
