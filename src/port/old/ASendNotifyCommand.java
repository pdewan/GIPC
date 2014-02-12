package port.old;

import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;

import java.nio.ByteBuffer;

public class ASendNotifyCommand implements Command {
	SendRegistrarAndNotifier sendNotifier;
	ByteBuffer message;
	int sendId;
	public ASendNotifyCommand(SendRegistrarAndNotifier theSendNotifier,
			ByteBuffer theMessage,
			int theSendId) {
		sendNotifier = theSendNotifier;
		message = theMessage;
		sendId= theSendId;		
	}
	@Override
	public void execute() {
		sendNotifier.notifyPortSend(null, message, sendId);
	}
}
