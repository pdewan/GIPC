package inputport.datacomm.duplex.object;


import port.AnAbstractPortLauncher;
import port.PortAccessKind;
import port.PortKind;
import port.PortMessageKind;





public class AnAbstractDuplexObjectClientPortLauncher extends AnAbstractPortLauncher   {
	
	public AnAbstractDuplexObjectClientPortLauncher(String aClientName) {
		super(aClientName);
	}
	public AnAbstractDuplexObjectClientPortLauncher() {
	}
	public AnAbstractDuplexObjectClientPortLauncher(String aClientName, String aServerHost, String aServerId, String aServerName) {
		super(aClientName, aServerHost, aServerId, aServerName);		
	}

	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.DUPLEX;
	}
	public PortKind getPortKind() {
		return PortKind.CLIENT_INPUT_PORT;
	}
	public PortMessageKind getPortMessageKind() {
		return PortMessageKind.OBJECT;
	}
	
	
}
