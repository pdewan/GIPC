package sessionport.rpc.group;

import sessionport.datacomm.group.GroupSessionPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.group.GroupRPCServerInputPort;

public interface GroupRPCSessionPort extends GroupRPCServerInputPort, DuplexRPCClientInputPort, GroupSessionPort<Object> {

}
