package port.sessionserver;

public class AServerPortDescription implements ServerPortDescription {
	String host;
	String id;
	String name;
	public AServerPortDescription (String aHost, String anID, String aName) {
		host = aHost;
		id = anID;
		name = aName;
	}
	@Override
	public String getHost() {
		return host;
	}

	@Override
	public String getID() {
		return id;
	}

	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof ServerPortDescription)) return false;
		ServerPortDescription otherSessionClientDescription = (ServerPortDescription) other;
		String otherHost = otherSessionClientDescription.getHost();
		String otherID = otherSessionClientDescription.getID();
		String otherName = otherSessionClientDescription.getName();
		return  
				(host == otherHost || (host != null && host.equals(otherHost))) && 
				(id == otherID || (id != null &&  id.equals(otherID))) &&
				(name == otherName || (name != null && name.equals(otherName)));
//				clientHost.equals(otherSessionClientDescription.getHost()) &&
//				clientID.equals(otherSessionClientDescription.getID()) &&
//				clientName.equals(otherSessionClientDescription.getName());
				
	}
	@Override
	public String toString() {
		String hostDescrip = host == null?"":"Host: " + host;
		String idDescrip = id == null?"" : " ID: " + id;
		return hostDescrip + " Name: " + name + idDescrip;
//		return "Host: " + host + " Name: " + name + " ID:" + id;
	}
	public AServerPortDescription () {
		
	}
	public void setHost(String clientHost) {
		this.host = clientHost;
	}
	public void setID(String clientID) {
		this.id = clientID;
	}
	public void setName(String clientName) {
		this.name = clientName;
	}
    public void initSerializedObject() {
		
	}
}
