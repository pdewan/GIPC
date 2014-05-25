package port.common;

import inputport.datacomm.ATrapperSelector;
import inputport.datacomm.TrapperSelector;
import inputport.datacomm.group.AGroupToUniSendTrapperSelector;
import inputport.datacomm.group.AGroupTrapperSelector;
import inputport.datacomm.group.GroupToUniSendTrapperSelector;
import inputport.datacomm.group.GroupTrapperSelector;
import inputport.datacomm.group.buffer.AGroupToUniSendBufferSelector;

import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Map;

import port.sessionserver.ServerPortDescription;
import replicatedserver.datacomm.duplex.anycast.AGetterAnyCastSendForwarderFactory;
import replicatedserverport.datacomm.simplex.AMultiToReplicatedTrapperFactory;
import replicatedserverport.datacomm.simplex.AMultiToReplicatedTrapperSelector;
import replicatedserverport.datacomm.simplex.ClientMultiToReplicatedTrapperSelector;
import replicatedserverport.datacomm.simplex.MultiToReplicatedTrapperFactory;
import replicatedserverport.datacomm.simplex.MultiToReplicatedTrapperSelector;
import util.trace.Tracer;

public class GlobalState {
	


//	public static TrapperSelector<ByteBuffer, ByteBuffer> clientBufferIPTrapperSelector = new ATrapperSelector();
	

	static TrapperSelector<ByteBuffer, ByteBuffer> serverBufferIPTrapperSelector = new ATrapperSelector();
	static TrapperSelector<ByteBuffer, ByteBuffer> clientBufferDuplexIPTrapperSelector = new ATrapperSelector();
	static TrapperSelector<ByteBuffer, ByteBuffer> serverBufferDuplexIPTrapperSelector = new ATrapperSelector();
	static GroupTrapperSelector<ByteBuffer, ByteBuffer> serverBufferGroupIPTrapperSelector = new AGroupTrapperSelector();
	static GroupToUniSendTrapperSelector<ByteBuffer, ByteBuffer> serverGroupToUniSendBufferTrapperSelector = new AGroupToUniSendBufferSelector();
	
	static TrapperSelector<Object, ByteBuffer> objectTranslatingIPTrapperSelector = new ATrapperSelector();
	static TrapperSelector<Object, Object> clientObjectForwardingIPTrapperSelector = new ATrapperSelector();
//	static GroupTrapperSelector<Object, Object> serverObjectForwardingIPTrapperSelector = new AGroupTrapperSelector();
	static TrapperSelector<Object, Object> simplexSerializableCallTrapper  = new ATrapperSelector();
	static TrapperSelector<Object, Object> duplexSerializableCallTrapper  = new ATrapperSelector();
	static GroupTrapperSelector<Object, Object> groupSerializableCallTrapper  = new AGroupTrapperSelector();

	
	public static GroupTrapperSelector<Object, Object> getGroupSerializableCallTrapper() {
		return groupSerializableCallTrapper;
	}

	public static void setGroupSerializableCallTrapper(
			GroupTrapperSelector<Object, Object> groupSerializableCallTrapper) {
		GlobalState.groupSerializableCallTrapper = groupSerializableCallTrapper;
	}
//
//	public static TrapperSelector<Object, Object> getDuplexSerializableCallTrapper() {
//		return duplexSerializableCallTrapper;
//	}
//
//	public static void setDuplexSerializableCallTrapper(
//			TrapperSelector<Object, Object> duplexSerializableCallTrapper) {
//		GlobalState.duplexSerializableCallTrapper = duplexSerializableCallTrapper;
//	}

	public static TrapperSelector<Object, Object> getSimplexSerializableCallTrapper() {
		return simplexSerializableCallTrapper;
	}

	public static void setSimplexSerializableCallTrapper(
			TrapperSelector<Object, Object> serializableCallTrapper) {
		GlobalState.simplexSerializableCallTrapper = serializableCallTrapper;
	}

	static TrapperSelector<Object, Object> clientObjectDuplexIPTrapperSelector = new ATrapperSelector();
	static TrapperSelector<Object, Object> serverObjectDuplexIPTrapperSelector = new ATrapperSelector();
//	static TrapperSelector<Object, Object> clientRelayingObjectDuplexIPTrapperSelector = new ATrapperSelector();

	static GroupTrapperSelector<Object, ByteBuffer> serverObjectGroupTranslatingIPTrapperSelector = new AGroupTrapperSelector();
	static GroupTrapperSelector<Object, Object> serverObjectGroupTrapperSelector = new AGroupTrapperSelector();
	static GroupToUniSendTrapperSelector<Object, Object> serverGroupToUniSendObjectTrapperSelector = new AGroupToUniSendTrapperSelector<Object, Object>();	
	
	static TrapperSelector<ByteBuffer, ByteBuffer> bufferDuplexSPFP2PTrapperSelector = new ATrapperSelector();
	static TrapperSelector<Object, Object> objectDuplexSPRelayedTrapperSelector = new ATrapperSelector();
	static GroupTrapperSelector<Object, Object> objectGroupSPRelayedTrapperSelector = new AGroupTrapperSelector();

	
	static TrapperSelector<Object, Object> sessionObjectDuplexForwardingIPTrapperSelector = new ATrapperSelector();
	
//	static GroupTrapperSelector<Object, ByteBuffer> sessionObjectGroupTranslatingIPTrapperSelector = new AGroupTrapperSelector();
	static GroupTrapperSelector<Object, Object> sessionObjectGroupTrapperSelector = new AGroupTrapperSelector();
	
	static TrapperSelector replicatedServerDuplexObjectTrapperSelector = new ATrapperSelector();
	
	static MultiToReplicatedTrapperSelector<Object, Object> clientUniToGroupSendObjectTrapperSelector = new AMultiToReplicatedTrapperSelector();
	static MultiToReplicatedTrapperSelector<ByteBuffer, ByteBuffer> clientUniToGroupSendBufferTrapperSelector = new AMultiToReplicatedTrapperSelector();
	
	static GroupTrapperSelector<Object, ByteBuffer> multiServerObjectGroupTranslatingIPTrapperSelector = new AGroupTrapperSelector();
	static GroupTrapperSelector<Object, Object> multiServerObjectGroupTrapperSelector = new AGroupTrapperSelector();
	static GroupToUniSendTrapperSelector<Object, Object> multiServerGroupToUniSendObjectTrapperSelector = new AGroupToUniSendTrapperSelector<Object, Object>();	
	
	

//  this is a group to group object pipe, so dont need other trappers
//	static TrapperSelector<Object, Object> sessionGroupToUniSendObjectTrapperSelector = new ATrapperSelector();
	
	



	static Map<String, ServerPortDescription[]> destinationToServers = new HashMap();
	
		
	//	public static void setCausalBroadcast(boolean yes) {
//		if (yes) {
//			Tracer.info("Setting factories for causal brodcast");
//			GroupTrapperFactory<Object, Object> trapperFactory = new ACausalClientObjectMessageFactory();
//			sessionObjectGroupTrapperSelector.setGroupSendTrapperFactory(trapperFactory);
//			sessionObjectGroupTrapperSelector.setReceiveTrapperFactory(trapperFactory);
//		} else {
//			Tracer.info("Setting factories for non causal brodcast");
//
//			sessionObjectGroupTrapperSelector.setGroupSendTrapperFactory(new AGroupSendMessageForwarderFactory<Object>());
//			sessionObjectGroupTrapperSelector.setReceiveTrapperFactory(new AReceiveMessageForwarderFactory<Object>());
//
//		}		
//	}
	public static void setAnyCast(boolean yes) {
		MultiToReplicatedTrapperFactory trapperFactory;

		if (yes) {
			Tracer.info("Setting factories for any cast");

			trapperFactory = new AGetterAnyCastSendForwarderFactory();
			
		} else {
			Tracer.info("Setting factories for non any cast");

			trapperFactory = new AMultiToReplicatedTrapperFactory();
//		getClientUniToGroupSendObjectTrapperSelector().setUniToGroupSendTrapperFactory(trapperFactory);
		ClientMultiToReplicatedTrapperSelector.getTrapperSelector().setUniToGroupSendTrapperFactory(trapperFactory);


		}		
	}
	
	
//	public static void setCausal(boolean yes) {
//		if (yes) {
//			ClientChannelBufferSendTrapperSelector.setClientChannelBufferSendTrapperFactory(
//					new ADelayingCausalClientChannelBufferFactory());
//		} else {
//			ClientChannelBufferSendTrapperSelector.setClientChannelBufferSendTrapperFactory(
//					new AClientChannelSendBufferForwarderFactory());
//		}		
//	}

	
	
	
	


//	public static TrapperSelector<ByteBuffer, ByteBuffer> getDuplexBufferClientIPTrapperSelector() {
//		return clientBufferDuplexIPTrapperSelector;
//	}
//
//	public static void setDuplexBufferClientIPTrapperSelector(
//			TrapperSelector<ByteBuffer, ByteBuffer> clientBufferDuplexIPTrapperSelector) {
//		GlobalState.clientBufferDuplexIPTrapperSelector = clientBufferDuplexIPTrapperSelector;
//	}

//	public static TrapperSelector<ByteBuffer, ByteBuffer> getDuplexBufferServerIPTrapperSelector() {
//		return serverBufferDuplexIPTrapperSelector;
//	}
//
//	public static void setDuplexBufferServerIPTrapperSelector(
//			TrapperSelector<ByteBuffer, ByteBuffer> serverBufferDuplexIPTrapperSelector) {
//		GlobalState.serverBufferDuplexIPTrapperSelector = serverBufferDuplexIPTrapperSelector;
//	}

//	public static GroupTrapperSelector<ByteBuffer, ByteBuffer> getServerBufferGroupIPTrapperSelector() {
//		return serverBufferGroupIPTrapperSelector;
//	}
//
//	public static void setServerBufferGroupIPTrapperSelector(
//			GroupTrapperSelector<ByteBuffer, ByteBuffer> serverBufferGroupIPTrapperSelector) {
//		Tracer.info("Setting Buffer Group IP Trapper Selector");
//		GlobalState.serverBufferGroupIPTrapperSelector = serverBufferGroupIPTrapperSelector;
//	}	

//	public static GroupTrapperSelector<Object, ByteBuffer> getServerObjectGroupTranslatingIPTrapperSelector() {
//		return serverObjectGroupTranslatingIPTrapperSelector;
//	}
//
//	public static void setServerObjectGroupTranslatingIPTrapperSelector(
//			GroupTrapperSelector<Object, ByteBuffer> serverObjectGroupIPTrapperSelectorOptimized) {
//		Tracer.info("Setting Object Gropup IP Trapper Selector");
//
//		GlobalState.serverObjectGroupTranslatingIPTrapperSelector = serverObjectGroupIPTrapperSelectorOptimized;
//	}

	

//	public static TrapperSelector<ByteBuffer, ByteBuffer> getBufferDuplexSPFP2PTrapperSelector() {
//		return bufferDuplexSPFP2PTrapperSelector;
//	}
//
//	public static void setBufferDuplexSPFP2PTrapperSelector(
//			TrapperSelector<ByteBuffer, ByteBuffer> bufferDuplexSPFP2PTrapperSelector) {
//		GlobalState.bufferDuplexSPFP2PTrapperSelector = bufferDuplexSPFP2PTrapperSelector;
//	}

//	public static TrapperSelector<Object, Object> getObjectDuplexSPRelayedTrapperSelector() {
//		return objectDuplexSPRelayedTrapperSelector;
//	}
//
//	public static void setObjectDuplexSPRelayedTrapperSelector(
//			TrapperSelector<Object, Object> objectDuplexSPRelayedTrapperSelector) {
//		Tracer.info("Setting Object Session Port Relayed Trapper Selector");
//		GlobalState.objectDuplexSPRelayedTrapperSelector = objectDuplexSPRelayedTrapperSelector;
//	}





//	public static TrapperSelector<Object, Object> getClientObjectDuplexIPTrapperSelector() {
//		return clientObjectDuplexIPTrapperSelector;
//	}
//
//	public static void setClientObjectDuplexIPTrapperSelector(
//			TrapperSelector<Object, Object> clientObjectDuplexIPTrapperSelector) {
//		Tracer.info("Setting Object Input Port client Object Duplex IP Trapper Selector");
//		GlobalState.clientObjectDuplexIPTrapperSelector = clientObjectDuplexIPTrapperSelector;
//	}

//	public static TrapperSelector<Object, Object> getServerObjectDuplexIPTrapperSelector() {
//		return serverObjectDuplexIPTrapperSelector;
//	}
//
//	public static void setServerObjectDuplexIPTrapperSelector(
//			TrapperSelector<Object, Object> serverObjectDuplexIPTrapperSelector) {
//		GlobalState.serverObjectDuplexIPTrapperSelector = serverObjectDuplexIPTrapperSelector;
//	}

//	public static TrapperSelector<Object, ByteBuffer> getObjectTranslatingIPTrapperSelector() {
//		return objectTranslatingIPTrapperSelector;
//	}
//
//	public static void setObjectTranslatingIPTrapperSelector(
//			TrapperSelector<Object, ByteBuffer> clientObjectTranslatingIPTrapperSelector) {
//		GlobalState.objectTranslatingIPTrapperSelector = clientObjectTranslatingIPTrapperSelector;
//	}

//	public static TrapperSelector<Object, ByteBuffer> getServerObjectTranslatingIPTrapperSelector() {
//		return serverObjectTranslatingIPTrapperSelector;
//	}
//
//	public static void setServerObjectTranslatingIPTrapperSelector(
//			TrapperSelector<Object, ByteBuffer> serverObjectTranslatingIPTrapperSelector) {
//		GlobalState.serverObjectTranslatingIPTrapperSelector = serverObjectTranslatingIPTrapperSelector;
//	}

//	public static TrapperSelector<Object, Object> getClientObjectForwardingIPTrapperSelector() {
//		return clientObjectForwardingIPTrapperSelector;
//	}
//
//	public static void setClientObjectForwardingIPTrapperSelector(
//			TrapperSelector<Object, Object> clientObjectForwardingIPTrapperSelector) {
//		GlobalState.clientObjectForwardingIPTrapperSelector = clientObjectForwardingIPTrapperSelector;
//	}

//	public static TrapperSelector<Object, Object> getServerObjectForwardingIPTrapperSelector() {
//		return serverObjectForwardingIPTrapperSelector;
//	}
//
//	public static void setServerObjectForwardingIPTrapperSelector(
//			TrapperSelector<Object, Object> serverObjectForwardingIPTrapperSelector) {
//		GlobalState.serverObjectForwardingIPTrapperSelector = serverObjectForwardingIPTrapperSelector;
//	}
//	public static GroupTrapperSelector<Object, Object> getServerObjectGroupTrapperSelector() {
//		return serverObjectGroupTrapperSelector;
//	}
//
//	public static void setServerObjectGroupTrapperSelector(
//			GroupTrapperSelector<Object, Object> optimizedObjectGroupIPForwardingTrapperSelector) {
//		GlobalState.serverObjectGroupTrapperSelector = optimizedObjectGroupIPForwardingTrapperSelector;
//	}
	
//	public static TrapperSelector<Object, Object> getSessionObjectDuplexForwardingIPTrapperSelector() {
//		return sessionObjectDuplexForwardingIPTrapperSelector;
//	}
//
//	public static void setSessionObjectDuplexForwardingIPTrapperSelector(
//			TrapperSelector<Object, Object> sessionObjectDuplexForwardingIPTrapperSelector) {
//		GlobalState.sessionObjectDuplexForwardingIPTrapperSelector = sessionObjectDuplexForwardingIPTrapperSelector;
//	}

//	public static GroupTrapperSelector<Object, ByteBuffer> getSessionObjectGroupTranslatingIPTrapperSelector() {
//		return sessionObjectGroupTranslatingIPTrapperSelector;
//	}
//
//	public static void setSessionObjectGroupTranslatingIPTrapperSelector(
//			GroupTrapperSelector<Object, ByteBuffer> objectGroupTranslatingSessionIPTrapperSelector) {
//		GlobalState.sessionObjectGroupTranslatingIPTrapperSelector = objectGroupTranslatingSessionIPTrapperSelector;
//	}

//	public static GroupTrapperSelector<Object, Object> getSessionObjectGroupTrapperSelector() {
//		return sessionObjectGroupTrapperSelector;
//	}
//
//	public static void setSessionObjectGroupTrapperSelector(
//			GroupTrapperSelector<Object, Object> optimizedObjectGroupSessionIPForwardingTrapperSelector) {
//		GlobalState.sessionObjectGroupTrapperSelector = optimizedObjectGroupSessionIPForwardingTrapperSelector;
//	}

//	public static TrapperSelector<Object, Object> getSessionGroupToUniSendObjectTrapperSelector() {
//		return sessionGroupToUniSendObjectTrapperSelector;
//	}
//
//	public static void setSessionGroupToUniSendObjectTrapperSelector(
//			TrapperSelector<Object, Object> unOptimizedObjectGroupSessionIPForwardingTrapperSelector) {
//		GlobalState.sessionGroupToUniSendObjectTrapperSelector = unOptimizedObjectGroupSessionIPForwardingTrapperSelector;
//	}
	
	public static void setServerPortDescription(String aDestination, ServerPortDescription[] aServerList) {
		destinationToServers.put(aDestination, aServerList);
	}
	
	public static ServerPortDescription[] getServerPortDescription(String aDestination) {
		return destinationToServers.get(aDestination);
	}
//	public static TrapperSelector<ByteBuffer, ByteBuffer> getSimplexBufferClientIPTrapperSelector() {
//		return clientBufferIPTrapperSelector;
//	}
//
//	public static void setSimplexBufferClientIPTrapperSelector(
//			TrapperSelector<ByteBuffer, ByteBuffer> clientBufferIPTrapperSelector) {
//		GlobalState.clientBufferIPTrapperSelector = clientBufferIPTrapperSelector;
//	}

//	public static TrapperSelector<ByteBuffer, ByteBuffer> getSimplexBufferServerIPTrapperSelector() {
//		return serverBufferIPTrapperSelector;
//	}
//
//	public static void setSimplexBufferServerIPTrapperSelector(
//			TrapperSelector<ByteBuffer, ByteBuffer> serverBufferIPTrapperSelector) {
//		GlobalState.serverBufferIPTrapperSelector = serverBufferIPTrapperSelector;
//	}



//	public static GroupToUniSendTrapperSelector<ByteBuffer, ByteBuffer> getServerGroupToUniSendBufferTrapperSelector() {
//		return serverGroupToUniSendBufferTrapperSelector;
//	}
//
//	public static void setServerGroupToUniSendBufferTrapperSelector(
//			GroupToUniSendTrapperSelector<ByteBuffer, ByteBuffer> serverGroupToUniSendBufferTrapperSelector) {
//		GlobalState.serverGroupToUniSendBufferTrapperSelector = serverGroupToUniSendBufferTrapperSelector;
//	}


//
//	public static GroupToUniSendTrapperSelector<Object, Object> getServerGroupToUniSendObjectTrapperSelector() {
//		return serverGroupToUniSendObjectTrapperSelector;
//	}
//
//	public static void setServerGroupToUniSendObjectTrapperSelector(
//			GroupToUniSendTrapperSelector<Object, Object> serverGroupToUniSendObjectTrapperSelector) {
//		GlobalState.serverGroupToUniSendObjectTrapperSelector = serverGroupToUniSendObjectTrapperSelector;
//	}



//	public static TrapperSelector<Object, Object> getClientRelayingObjectDuplexIPTrapperSelector() {
//		return clientRelayingObjectDuplexIPTrapperSelector;
//	}
//
//	public static void setClientRelayingObjectDuplexIPTrapperSelector(
//			TrapperSelector<Object, Object> clientRelayingObjectDuplexIPTrapperSelector) {
//		GlobalState.clientRelayingObjectDuplexIPTrapperSelector = clientRelayingObjectDuplexIPTrapperSelector;
//	}


//
//	public static TrapperSelector<Object, Object> getReplicatedServerDuplexObjectTrapperSelector() {
//		return replicatedServerDuplexObjectTrapperSelector;
//	}
//
//	public static void setReplicatedServerDuplexObjectTrapperSelector(
//			TrapperSelector<Object, Object> replicatedServerObjectDuplexTrapperSelector) {
//		GlobalState.replicatedServerDuplexObjectTrapperSelector = replicatedServerObjectDuplexTrapperSelector;
//	}


//
//	public static UniToGroupTrapperSelector getClientUniToGroupSendObjectTrapperSelector() {
//		return clientUniToGroupSendObjectTrapperSelector;
//	}
//	
//
//	public static void setClientUniToGroupSendObjectTrapperSelector(
//			UniToGroupTrapperSelector clientUniToAllSendTrapperSelector) {
//		GlobalState.clientUniToGroupSendObjectTrapperSelector = clientUniToAllSendTrapperSelector;
//	}

//	public static UniToGroupTrapperSelector getClientUniToGroupSendBufferTrapperSelector() {
//		return clientUniToGroupSendBufferTrapperSelector;
//	}
//
//	public static void setClientUniToGroupSendBufferTrapperSelector(
//			UniToGroupTrapperSelector clientUniToAllSendTrapperSelector) {
//		GlobalState.clientUniToGroupSendBufferTrapperSelector = clientUniToAllSendTrapperSelector;
//	}
//	public static GroupTrapperSelector<Object, ByteBuffer> getMultiServerObjectGroupTranslatingIPTrapperSelector() {
//		return multiServerObjectGroupTranslatingIPTrapperSelector;
//	}
//
//	public static void setMultiServerObjectGroupTranslatingIPTrapperSelector(
//			GroupTrapperSelector<Object, ByteBuffer> multiServerObjectGroupTranslatingIPTrapperSelector) {
//		GlobalState.multiServerObjectGroupTranslatingIPTrapperSelector = multiServerObjectGroupTranslatingIPTrapperSelector;
//	}

//	public static GroupTrapperSelector<Object, Object> getMultiServerObjectGroupTrapperSelector() {
//		return multiServerObjectGroupTrapperSelector;
//	}
//
//	public static void setMultiServerObjectGroupTrapperSelector(
//			GroupTrapperSelector<Object, Object> multiServerObjectGroupTrapperSelector) {
//		GlobalState.multiServerObjectGroupTrapperSelector = multiServerObjectGroupTrapperSelector;
//	}

//	public static GroupToUniSendTrapperSelector<Object, Object> getMultiServerGroupToUniSendObjectTrapperSelector() {
//		return multiServerGroupToUniSendObjectTrapperSelector;
//	}
//
//	public static void setMultiServerGroupToUniSendObjectTrapperSelector(
//			GroupToUniSendTrapperSelector<Object, Object> multiServerGroupToUniSendObjectTrapperSelector) {
//		GlobalState.multiServerGroupToUniSendObjectTrapperSelector = multiServerGroupToUniSendObjectTrapperSelector;
//	}

//	public static GroupTrapperSelector<Object, Object> getObjectGroupSPRelayedTrapperSelector() {
//		return objectGroupSPRelayedTrapperSelector;
//	}



//	public static void setObjectGroupSPRelayedTrapperSelector(
//			GroupTrapperSelector<Object, Object> objectGroupSPRelayedTrapperSelector) {
//		GlobalState.objectGroupSPRelayedTrapperSelector = objectGroupSPRelayedTrapperSelector;
//	}

	static {
//		delayQueueConsumer = new ADelayQueueConsumer(delayQueue);		
//		delayQueueConsumerThread = new Thread(delayQueueConsumer);
//		delayQueueConsumerThread.start();
	}	

}
