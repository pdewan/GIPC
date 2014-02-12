package extraip;

import inputport.rpc.duplex.RPCReturnValue;


public interface BuggyRPCReturnValueReceiver {

	public Object getReturnValue();
	public void  messageReceived(String remoteClientName, RPCReturnValue message);

	

}