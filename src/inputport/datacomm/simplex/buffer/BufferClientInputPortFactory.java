package inputport.datacomm.simplex.buffer;

import inputport.datacomm.simplex.SimplexClientInputPort;


public interface BufferClientInputPortFactory {
	SimplexClientInputPort createClientInputPort(String theHost, String theServerId, String theClientName);
}
