package replicatedserverport.rpc.duplex;

import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.rpc.duplex.ADuplexRPCClientInputPort;
import inputport.rpc.duplex.DuplexClientSerializableCallTrapperSelector;



public class AReplicatedServerDuplexRPCClientPort extends ADuplexRPCClientInputPort {
	public AReplicatedServerDuplexRPCClientPort(DuplexClientInputPort<Object> anObjectClientInputPort) {
		super(anObjectClientInputPort);		
	}
	protected ReceiveTrapper<Object, Object> getSerializableCallReceiveTrapper () {
		return ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveReceiptRegistrarAndNotifier);
	}
	protected SendTrapper<Object, Object> getSerializableCallSendTrapper () {
		return ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().createSendTrapper(this, objectClientInputPort);
	}

}
