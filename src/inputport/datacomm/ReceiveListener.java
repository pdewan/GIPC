package inputport.datacomm;
public interface ReceiveListener<MessageType> {
	// not making port as an argument as type of port keeps evolving. Better to initialize in constructor.
	// restriction that cannot have a listener for multiple ports, which seems ok.
	void messageReceived(String aSourceName, MessageType aMessage);
}
