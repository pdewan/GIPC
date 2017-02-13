package inputport.rpc.group;

import inputport.rpc.duplex.AnAbstractDuplexRPCServerPortLauncher;
import port.PortAccessKind;



public abstract class AnAbstractGroupRPCServerPortLauncher extends AnAbstractDuplexRPCServerPortLauncher    {

	public AnAbstractGroupRPCServerPortLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}
	
	public AnAbstractGroupRPCServerPortLauncher() {
	}
	
	@Override
	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.GROUP;
	}
	

	

	
}
