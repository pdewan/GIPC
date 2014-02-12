package oldrpcip;

import java.io.Serializable;


public interface RPCReturnValueReceiver {

	public Serializable getReturnValue();
	public void  messageReceived(String remoteClientName, RPCReturnValue message);

	

}