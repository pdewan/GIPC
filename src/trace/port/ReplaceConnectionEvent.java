package trace.port;

public interface ReplaceConnectionEvent {
	public Object getSource() ;
	public Object getOldDestination();
	public Object getNewDestination() ;
	public boolean isSent();
	public boolean deleteOldDestination();
}
