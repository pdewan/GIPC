package port.ot;

import util.session.SentMessage;

public interface OTManager {
	public void processReceivedMessage(EditWithOTTimeStamp receivedTSEdit);
	public EditWithOTTimeStamp processSentEdit(Edit edit );
	public void storeSentMessage(SentMessage message);

//	public EditWithOTTimeStamp processSentEdit(Edit edit );
//	//public void setCommunicator(Communicator theCommunicator);
//	public void put(ReceivedMessage message);
//	public void put(SentMessage message);
//	public void setReceivedMessageQueue(BoundedBuffer<ReceivedMessage> theBuffer) ;
//	public void setSentMessageQueue(BoundedBuffer<SentMessage> theBuffer) ;
}
