package inputport.rpc.duplex;

import inputport.datacomm.duplex.DuplexInputPort;
import inputport.rpc.RPCInputPort;
import inputport.rpc.RPCRegistry;

public interface DuplexRPCInputPort extends  RPCInputPort, DuplexRPC, RPCRegistry, DuplexInputPort<Object>{
	LocalRemoteReferenceTranslator getLocalRemoteReferenceTranslator();
	DuplexInputPort<Object> getDuplexInputPort();
	void setDuplexInputPort(DuplexInputPort<Object> newVal);
//	String getLastSenderOfNonReplyMessage();	
//	void setLastSenderOfNonReplyMesasage(String newVal);
}
