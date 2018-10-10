package replicatedserverport.rpc.duplex.earliestresponse;

import inputport.datacomm.TrapperFactory;
import inputport.rpc.duplex.ADuplexCallTrapperFactory;
import replicatedserverport.datacomm.duplex.earliest.AnEarliestAcceptingReplicatedPortLauncherSupport;
import replicatedserverport.rpc.duplex.ReplicatedDuplexClientSerializableCallTrapperSelector;

public class AnEarliestAcceptingReplicatedDuplexRPCLauncherSupport extends AnEarliestAcceptingReplicatedPortLauncherSupport {
	public  void setFactories() {
		// does it make sense to have data  port without rpc support, should super be combined with
		// this class. I suppose one could replciate something other than a session also ...
		super.setFactories(); 
		TrapperFactory<Object, Object> aFactory = new ADuplexCallTrapperFactory();	

		ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
		setSendTrapperFactory(aFactory);	
		ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
		setReceiveTrapperFactory(aFactory);


	}


}
