package port.old;

import inputport.datacomm.group.GroupServerInputPort;








public class PrintingReplyingGroupSendingTypedReceiver extends PrintingReplyingObjectReceiver {
	protected GroupServerInputPort<Object> groupPort;
	public PrintingReplyingGroupSendingTypedReceiver(GroupServerInputPort<Object> thePort) {
		super(thePort);
		groupPort = thePort;
//		duplexPort = thePort;
		
	}
	@Override
	public void messageReceived(String senderName, Object message) {
		super.messageReceived(senderName, message);
//		duplexPort.reply(message);
		groupPort.sendOthers(message);
	}

}
