package inputport;


public interface RemoteEndPointProperties extends QueryablePort, ConnectionsQueryable{
	String getLogicalRemoteEndPoint();
	String getPhysicalRemoteEndPoint();
	void setPhysicalRemoteEndPoint(String newVal);
	void setLogicalRemoteEndPoint(String newVal);
	


}
