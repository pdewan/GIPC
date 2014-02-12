package port.delay;


public interface DelayQueue<MessageType>  {	
	void add (DelayableMessage<MessageType> aDelayableMessage);
	DelayableMessage<MessageType> take() throws InterruptedException;
	public boolean getCoupled() ;
	public  void setCoupled(boolean newVal);
}