package inputport.rpc.duplex;


import port.PortKind;
public class AnAbstractDuplexRPCClientPortLauncher extends ADuplexRPCPortLauncher   {		

//public class AnAbstractDuplexRPCClientPortLauncher extends AnAbstractPortLauncher   {		
	public AnAbstractDuplexRPCClientPortLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}
//	protected PortAccessKind getPortAccessKind() {
//		return PortAccessKind.DUPLEX;
//	}
	@Override
	public DuplexRPCClientInputPort getRPCClientPort() {
		return (DuplexRPCClientInputPort) getInputPort();
	}
	public PortKind getPortKind() {
		return PortKind.CLIENT_INPUT_PORT;
	}
//	protected PortMessageKind getPortMessageKind() {
//		return PortMessageKind.RPC;
//	}	
}
