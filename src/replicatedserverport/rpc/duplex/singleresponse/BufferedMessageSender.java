package replicatedserverport.rpc.duplex.singleresponse;

public interface BufferedMessageSender {
	void sendBufferedMessage(String clientName, Object message);

}
