package util.trace.port;

public interface ConnectionEvent {
	public Object getSource();
	public Object getDestination();
	boolean isSent();

}
