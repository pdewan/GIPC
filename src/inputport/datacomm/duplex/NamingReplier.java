package inputport.datacomm.duplex;

public interface NamingReplier<MessageType> {

	void reply(String aSource, MessageType aMessage);

}
