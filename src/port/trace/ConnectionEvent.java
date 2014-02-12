package port.trace;

public interface ConnectionEvent {
	public Object getSource();
	public Object getDestination();
	boolean isSent();

}
