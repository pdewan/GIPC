package port.relay;

import inputport.rpc.group.GroupRPCServerInputPort;

import java.util.Collection;
import java.util.Set;

import sessionport.datacomm.duplex.object.relayed.MessageWithSource;
import util.trace.Tracer;


public class ARelayer implements Relayer{  
	GroupRPCServerInputPort groupRPCServerInputPort;
	public ARelayer(
			GroupRPCServerInputPort aDuplexPCServerInputPort) {
		groupRPCServerInputPort = aDuplexPCServerInputPort;
	}
	
//	@Override
//	public void relay(String aSessionName, String remoteEnd, MessageWithSource message) {
//		Tracer.info(this, "Relaying message:" + message + " to " + remoteEnd);		
//		groupRPCServerInputPort.send(remoteEnd, message);		
//	}
//	
//	@Override
//	public void relay(String sessionName, Set<String> remoteEnds, MessageWithSource message) {
//		Tracer.info(this, "Relaying message:" + message + " to " + remoteEnds);		
//		groupRPCServerInputPort.send(remoteEnds, message);		
//	}
//
//	@Override
//	public void relayOthers(String aSessionName, MessageWithSource message) {
//		Tracer.info(this, "Relaying message:" + message + " to others");		
//		groupRPCServerInputPort.sendOthers(message);		
//
//		
//	}
	@Override
	public void relay(String remoteEnd, MessageWithSource message) {
		Tracer.info(this, "Relaying message:" + message + " to " + remoteEnd);		
		groupRPCServerInputPort.send(remoteEnd, message);		
	}
	
	@Override
	public void relay(Collection<String> remoteEnds, MessageWithSource message) {
		Tracer.info(this, "Relaying message:" + message + " to " + remoteEnds);		
		groupRPCServerInputPort.send(remoteEnds, message);		
	}

	@Override
	public void relayOthers(MessageWithSource message) {
		Tracer.info(this, "Relaying message:" + message + " to others");		
		groupRPCServerInputPort.sendOthers(message);		

		
	}
	
	
//	//utlity functions
//
//	public static String uniqueRelayerNameToSessionName(String aUniqueName) {
//		int separatorIndex = aUniqueName.indexOf(':');
//		return aUniqueName.substring(0, separatorIndex);
//	}
//	
//	public static String uniqueRelayerNameToLocalName(String aUniqueName) {
//		int separatorIndex = aUniqueName.indexOf(':');
//		return aUniqueName.substring (separatorIndex + 1, aUniqueName.length());
//	}
//	
//	public static String localToUniqueRelayerName(String aSessionName, String aLocalName) {
//		return aSessionName + ":" + aLocalName;
//	}


}
