package examples.gipc.consensus.paxos;

import inputport.InputPort;
import inputport.datacomm.NamingSender;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.object.MultipleSendGroupForwarderFactory;


public class APaxosMultiCastFactory  implements MultipleSendGroupForwarderFactory {


	@Override
	public GroupNamingSender<Object> createMultipleSendGroupForwarder(
			InputPort anInputPort, NamingSender<Object> aDestination) {
		return new APaxosMultiCaster(anInputPort, aDestination);
	}

}
