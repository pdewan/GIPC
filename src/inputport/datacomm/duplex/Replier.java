package inputport.datacomm.duplex;

public interface Replier<MessageType> extends NamingReplier<MessageType>{

	public void reply(MessageType message);

}
