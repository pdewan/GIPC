package oldtypedip;

import java.io.Serializable;




public class PrintingReplyingTypedReceiver extends PrintingTypedReceiveListener {
	protected TypedGroupServerInputPort port;
	public PrintingReplyingTypedReceiver(TypedGroupServerInputPort thePort) {
		super(null);
		port = thePort;
	}
	@Override
	public void messageReceived(String senderName, Serializable message) {
		super.messageReceived(senderName, message);
		port.reply(message);
		port.sendOthers(message);
	}

}
