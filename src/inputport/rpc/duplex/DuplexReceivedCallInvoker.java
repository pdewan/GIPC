package inputport.rpc.duplex;

import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.ReceivedCallInvoker;

public interface DuplexReceivedCallInvoker extends ReceivedCallInvoker{
	public  DuplexInputPort<Object> getReplier();


}
