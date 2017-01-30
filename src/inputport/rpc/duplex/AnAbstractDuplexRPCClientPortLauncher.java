package inputport.rpc.duplex;


import port.AnAbstractPortLauncher;
import port.PortAccessKind;
import port.PortKind;
import port.PortMessageKind;
public class AnAbstractDuplexRPCClientPortLauncher extends AnAbstractDuplexRPCPortLauncher   {		

//public class AnAbstractDuplexRPCClientPortLauncher extends AnAbstractPortLauncher   {		
	public AnAbstractDuplexRPCClientPortLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}
//	protected PortAccessKind getPortAccessKind() {
//		return PortAccessKind.DUPLEX;
//	}
	protected PortKind getPortKind() {
		return PortKind.CLIENT_INPUT_PORT;
	}
//	protected PortMessageKind getPortMessageKind() {
//		return PortMessageKind.RPC;
//	}	
}
