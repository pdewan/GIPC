package port.sessionserver;

import java.io.Serializable;

public interface ServerPortDescription extends Serializable{
	String getName();
	String getID();
	String getHost();	
	void setName(String newVal);
	void setID(String newVal);
	void setHost(String newVal);


}
