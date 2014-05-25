package inputport.datacomm.duplex.object.echoer.example;

import port.AnAbstractPortLauncher;
import port.PortAccessKind;
import port.PortKind;
import port.PortMessageKind;



public abstract class AnAbstractDuplexObjectServerPortLauncher extends AnAbstractPortLauncher   {

	public AnAbstractDuplexObjectServerPortLauncher(String aServerName,
			String aServerPort) {
		super (aServerName, aServerPort);
	}	
	@Override
	protected PortAccessKind getPortAccessKind() {
		return PortAccessKind.DUPLEX;
	}	
	public AnAbstractDuplexObjectServerPortLauncher() {
	}	
	@Override
	protected PortKind getPortKind() {
		return PortKind.SERVER_INPUT_PORT;
	}
	@Override
	protected PortMessageKind getPortMessageKind() {
		return PortMessageKind.OBJECT;
	}
	

	
}
