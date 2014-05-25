package extraip;

import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPortFactory;
import port.sessionserver.ServerPortDescription;

public interface FaultTolerantDuplexInputPortFactory<MessageType> 
	extends DuplexServerInputPortFactory<MessageType> {
	
	DuplexClientInputPort<MessageType> createFaultTolerantDuplexClientInputPort(
			ServerPortDescription[] aServerList, String aLogicalServerName, String aClientName);
	

}
