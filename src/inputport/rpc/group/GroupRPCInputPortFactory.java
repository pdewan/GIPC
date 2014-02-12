package inputport.rpc.group;

import inputport.rpc.duplex.DuplexRPCClientInputPort;

public interface GroupRPCInputPortFactory {
	public GroupRPCServerInputPort createGroupRPCServerInputPort(String theServerId, String theServerName);
	public DuplexRPCClientInputPort createDuplexRPCClientInputPort(String theServerHost, String theServerId, String theServerName, String theClientName);

}
