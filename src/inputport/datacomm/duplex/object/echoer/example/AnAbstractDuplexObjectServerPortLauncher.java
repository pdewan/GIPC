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
	public PortAccessKind getPortAccessKind() {
		return PortAccessKind.DUPLEX;
	}	
	public AnAbstractDuplexObjectServerPortLauncher() {
	}	
	@Override
	public PortKind getPortKind() {
		return PortKind.SERVER_INPUT_PORT;
	}
	@Override
	public PortMessageKind getPortMessageKind() {
		return PortMessageKind.OBJECT;
	}
	

	
}
