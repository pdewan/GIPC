package sessionport.datacomm.duplex.object.relayed;

import java.io.Serializable;

public interface MessageWithSource extends Serializable {
	Object getMessage() ;
	void setMessage(Object message) ;
	String getSource();
	void setSource(String source);
}
