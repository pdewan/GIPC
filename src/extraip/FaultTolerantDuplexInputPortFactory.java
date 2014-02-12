package extraip;

import port.sessionserver.ServerPortDescription;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPortFactory;

public interface FaultTolerantDuplexInputPortFactory<MessageType> 
	extends DuplexServerInputPortFactory<MessageType> {
	
	DuplexClientInputPort<MessageType> createFaultTolerantDuplexClientInputPort(
			ServerPortDescription[] aServerList, String aLogicalServerName, String aClientName);
	

}
