package examples.gipc.consensus.paxos.scenarios;

import inputport.InputPort;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupSendTrapperFactory;


public class APaxosMultiCastFactory  implements GroupSendTrapperFactory{


//	@Override
//	public GroupNamingSender<Object> createMultipleSendGroupForwarder(
//			InputPort anInputPort, NamingSender<Object> aDestination) {
//		return new APaxosMultiCaster(anInputPort, aDestination);
//	}

	@Override
	public GroupSendTrapper createGroupSendTrapper(InputPort anInputPort,
			GroupNamingSender aDestination) {
		return new APaxosMultiCaster(anInputPort, aDestination);
	}

}
