package inputport.datacomm.group.buffer;

import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.group.AnAbstractGroupServerInputPort;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Set;




public class ABufferGroupServerInputPort extends AnAbstractGroupServerInputPort<ByteBuffer>{

	public ABufferGroupServerInputPort(DuplexServerInputPort<ByteBuffer> aDuplexServerInputPort) {
		super(aDuplexServerInputPort);
		groupToUniSendTrapper = ServerGroupToUniSendSendBufferTrapperSelector.getTrapperSelector().createGroupToUniSendTrapper(this, duplexServerInputPort);
		setGroupToUniSendTrapper(groupToUniSendTrapper);

//		groupToUniSendTrapper = GlobalState.getServerGroupToUniSendBufferTrapperSelector().createGroupToUniSendTrapper(this, duplexServerInputPort);
//		groupSendTrapper = GlobalState.getServerBufferGroupIPTrapperSelector().createGroupSendTrapper(this, groupToUniSendTrapper);
		groupSendTrapper = ServerBufferGroupIPTrapperSelector.getTrapperSelector().createGroupSendTrapper(this, groupToUniSendTrapper);
		setGroupSendTrapper(groupSendTrapper);
		
	//		receiveTrapper = GlobalState.getServerBufferGroupIPTrapperSelector().createReceiveTrapper(this, receiveRegistrarAndNotifier);

		receiveTrapper = ServerBufferGroupIPTrapperSelector.getTrapperSelector().createReceiveTrapper(this, receiveRegistrarAndNotifier);
		setReceiveTrapper(receiveTrapper);
	}
	@Override
	public void send(Collection<String> clientNames, ByteBuffer message) {
		groupSendTrapper.send(clientNames, message);

	}
	public ReceiveReturnMessage<ByteBuffer> receive() {
		return receive(getSender());		

	}
	@Override
	public ReceiveReturnMessage<ByteBuffer> receive(String aSource) {
		System.err.println("Receive not implemented");
		return null;
	}
}
