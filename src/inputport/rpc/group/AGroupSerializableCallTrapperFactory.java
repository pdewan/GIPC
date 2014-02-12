package inputport.rpc.group;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupTrapperFactory;
import inputport.rpc.duplex.ADuplexCallReceiveTrapper;

public class AGroupSerializableCallTrapperFactory implements GroupTrapperFactory<Object, Object> {

	

	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<Object> aReceiveNotifier) {
		return new ADuplexCallReceiveTrapper(anInputPort, aReceiveNotifier);
	}

	@Override
	public GroupSendTrapper<Object, Object> createGroupSendTrapper(
			InputPort anInputPort, GroupNamingSender<Object> destination) {
		return new AGroupSerializableCallSendTrapper(anInputPort, destination);
	}
	

}
