package inputport.datacomm;

public class SendToUnconnectedPortException extends RuntimeException{
	public SendToUnconnectedPortException(String aMessage) {
		super(aMessage);
	}

}
