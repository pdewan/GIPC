package inputport.rpc.group;

import inputport.datacomm.group.GroupServerInputPort;
import inputport.rpc.duplex.DuplexRPCServerInputPort;

public interface GroupRPCServerInputPort extends 
	GroupServerInputPort<Object>, 
	DuplexRPCServerInputPort,
	GroupRPC{

}
