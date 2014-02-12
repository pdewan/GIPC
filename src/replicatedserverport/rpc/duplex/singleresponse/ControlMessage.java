package replicatedserverport.rpc.duplex.singleresponse;

import java.io.Serializable;

public interface ControlMessage extends Serializable {
	int getServerMessageCount();
	void setServerMessageCount(int serverMessageCount);
	String getServerChoice() ;
	void setServerChoice(String serverChosen);
}
