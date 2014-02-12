package inputport.rpc.duplex;

import java.util.concurrent.BlockingQueue;


public interface RPCReturnValueQueue {

//	public BlockingQueue getReturnValueQueue();
	public void  putReturnValue(RPCReturnValue anRPCReturnValue);
	Object takeReturnValue();
	String getRemoteEnd();
	BlockingQueue<RPCReturnValue> returnValueQueue();
	

	

}