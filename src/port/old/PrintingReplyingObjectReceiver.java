package port.old;

import inputport.datacomm.duplex.DuplexServerInputPort;








public class PrintingReplyingObjectReceiver extends PrintingTypedReceiveListener {
	protected DuplexServerInputPort<Object> duplexPort;
	public PrintingReplyingObjectReceiver(DuplexServerInputPort<Object> thePort) {
		super(thePort);
		duplexPort = thePort;
		
	}
	@Override
	public void messageReceived(String senderName, Object message) {
		super.messageReceived(senderName, message);
		duplexPort.reply(message);
//		port.sendOthers(message);
	}

}
