package inputport.rpc.duplex;

import java.io.Serializable;

public interface RemoteSerializable extends Serializable {
	String getRemoteEndName();
	String getTypeName();
	String getObjectName();

}
