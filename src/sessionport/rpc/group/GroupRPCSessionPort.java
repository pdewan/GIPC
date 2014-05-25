package sessionport.rpc.group;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.group.GroupRPCServerInputPort;
import sessionport.datacomm.group.GroupSessionPort;

public interface GroupRPCSessionPort extends GroupRPCServerInputPort, DuplexRPCClientInputPort, GroupSessionPort<Object> {

}
