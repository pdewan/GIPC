package port.jitter;

import inputport.InputPort;
import inputport.datacomm.ReceiveNotifier;
import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.group.GroupNamingSender;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupTrapperFactory;

public class AJitterControllingGroupTrapperFactory 
	implements GroupTrapperFactory<Object, Object>{

	@Override
	public GroupSendTrapper<Object, Object> createGroupSendTrapper(
			InputPort anInputPort, GroupNamingSender<Object> destination) {
		return new ATimeStampingGroupSendTrapper(destination);
	}

	@Override
	public ReceiveTrapper<Object, Object> createReceiveTrapper(
			InputPort anInputPort, ReceiveNotifier<Object> receiveNotifier) {
		return new AJitterControllingReceiveTrapper(anInputPort, receiveNotifier);

	}

}
