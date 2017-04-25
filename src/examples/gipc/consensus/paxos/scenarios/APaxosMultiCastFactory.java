package examples.gipc.consensus.paxos.scenarios;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupSendTrapperFactory;
import inputport.datacomm.group.object.MultipleSendGroupForwarderFactory;


public class APaxosMultiCastFactory  implements GroupSendTrapperFactory{


//	@Override
//	public GroupNamingSender<Object> createMultipleSendGroupForwarder(
//			InputPort anInputPort, NamingSender<Object> aDestination) {
//		return new APaxosMultiCaster(anInputPort, aDestination);
//	}

	@Override
	public GroupSendTrapper createGroupSendTrapper(InputPort anInputPort,
			GroupNamingSender aDestination) {
		return new APaxosMultiCaster(aDestination);
	}

}
