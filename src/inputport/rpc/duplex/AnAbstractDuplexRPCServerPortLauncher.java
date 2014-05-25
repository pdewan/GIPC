package inputport.rpc.duplex;

import port.AnAbstractPortLauncher;
import port.PortAccessKind;
import port.PortKind;
import port.PortMessageKind;



public abstract class AnAbstractDuplexRPCServerPortLauncher extends AnAbstractPortLauncher   {

	public AnAbstractDuplexRPCServerPortLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}	
	@Override
	protected PortAccessKind getPortAccessKind() {
		return PortAccessKind.DUPLEX;
	}	
	public AnAbstractDuplexRPCServerPortLauncher() {
	}	
	@Override
	protected PortKind getPortKind() {
		return PortKind.SERVER_INPUT_PORT;
	}
	@Override
	protected PortMessageKind getPortMessageKind() {
		return PortMessageKind.RPC;
	}
	

	
}
