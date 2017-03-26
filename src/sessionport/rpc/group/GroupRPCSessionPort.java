package sessionport.rpc.group;

import inputport.rpc.duplex.DuplexRPCClientInputPort;
import inputport.rpc.group.GroupRPCServerInputPort;
import sessionport.datacomm.group.GroupSessionPort;
import sessionport.rpc.duplex.DuplexRPCSessionPort;

public interface GroupRPCSessionPort extends DuplexRPCSessionPort, GroupRPCServerInputPort, DuplexRPCClientInputPort, GroupSessionPort<Object> {

}
