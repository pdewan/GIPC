package trace.port;

public interface MessageEvent {
	public Object getSource();
	public Object getDestination();
	boolean isSent();
	boolean isForwarded();

}
