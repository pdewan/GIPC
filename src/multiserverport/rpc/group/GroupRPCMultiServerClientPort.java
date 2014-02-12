package multiserverport.rpc.group;

import inputport.rpc.group.GroupRPC;
import multiserverport.datacomm.group.GroupMultiServerClientPort;
import multiserverport.rpc.duplex.DuplexRPCMultiServerClientPort;

public interface GroupRPCMultiServerClientPort extends 
	GroupMultiServerClientPort<Object>, 
	DuplexRPCMultiServerClientPort,
	GroupRPC{

}
