package port.delay;

public interface MessageDeliverer {
	public void deliver(String aRemoteEnd, Object aMessage);

}
