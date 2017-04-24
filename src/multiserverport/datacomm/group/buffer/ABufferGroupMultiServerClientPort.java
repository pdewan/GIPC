package multiserverport.datacomm.group.buffer;


import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.duplex.object.explicitreceive.ReceiveReturnMessage;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.buffer.ServerBufferGroupIPTrapperSelector;
import inputport.datacomm.group.buffer.ServerGroupToUniSendSendBufferTrapperSelector;

import java.nio.ByteBuffer;
import java.util.Collection;
import java.util.Set;

import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;
import multiserverport.datacomm.group.AnAbstractGroupMultiServerClientPort;
import multiserverport.datacomm.group.GroupMultiServerClientPort;



public class ABufferGroupMultiServerClientPort 
	extends AnAbstractGroupMultiServerClientPort<ByteBuffer> 
	implements GroupMultiServerClientPort<ByteBuffer>{
	DuplexMultiServerClientPort<ByteBuffer> duplexMultiServerClientPort;

	public ABufferGroupMultiServerClientPort(DuplexMultiServerClientPort<ByteBuffer> aDuplexMultiServerClientPort) {
		super(aDuplexMultiServerClientPort);
		duplexMultiServerClientPort = aDuplexMultiServerClientPort;
//		groupToUniSendTrapper = GlobalState.getServerGroupToUniSendBufferTrapperSelector().
//		groupToUniSendTrapper = ServerGroupToUniSendSendBufferTrapperSelector.getTrapperSelector().
//		createGroupToUniSendTrapper(this, duplexMultiServerClientPort);
////		groupSendTrapper = GlobalState.getServerBufferGroupIPTrapperSelector().
//		groupSendTrapper = ServerBufferGroupIPTrapperSelector.getTrapperSelector().
//				createGroupSendTrapper(this, groupToUniSendTrapper);
		setGroupSendTrapper(createGroupSendTrapper());
		
//		receiveTrapper = GlobalState.getServerBufferGroupIPTrapperSelector().
//		receiveTrapper = ServerBufferGroupIPTrapperSelector.getTrapperSelector().
//				createReceiveTrapper(this, receiveRegistrarAndNotifier);
		setReceiveTrapper(createReceiveTrapper());
	}
	protected GroupSendTrapper<ByteBuffer, ByteBuffer> createGroupSendTrapper() {
//		groupToUniSendTrapper = GlobalState.getServerGroupToUniSendBufferTrapperSelector().
		groupToUniSendTrapper = ServerGroupToUniSendSendBufferTrapperSelector.getTrapperSelector().
		createGroupToUniSendTrapper(this, duplexMultiServerClientPort);
//		groupSendTrapper = GlobalState.getServerBufferGroupIPTrapperSelector().
		return ServerBufferGroupIPTrapperSelector.getTrapperSelector().
				createGroupSendTrapper(this, groupToUniSendTrapper);
	}
	
	protected ReceiveTrapper<ByteBuffer, ByteBuffer> createReceiveTrapper() {
		return ServerBufferGroupIPTrapperSelector.getTrapperSelector().
		createReceiveTrapper(this, receiveRegistrarAndNotifier);
	}
	
	
	

	
	@Override
	public void send(Collection<String> clientNames, ByteBuffer message) {
		groupSendTrapper.send(clientNames, message);

	}
	@Override
	public ReceiveReturnMessage<ByteBuffer> receive() {
		return receive(getSender());
	}

	@Override
	public ReceiveReturnMessage<ByteBuffer> receive(String aSource) {
		System.err.println("Receive not implemented");
		return null;
	}
//	@Override
//	public String getRemoteEndPoint() {
//		return duplexMultiServerClientPort.getRemoteEndPoint();
//	}
	
	
	
	
//	@Override
//	public ReceiveNotifier<ByteBuffer> getReceiveTrapper() {
//		return duplexServerInputPort.getReceiveTrapper();
//	}
//	@Override
//	public void setReceiveTrapper(ReceiveNotifier<ByteBuffer> newVal) {
//		duplexServerInputPort.setReceiveTrapper(newVal)	;
//	}
//	@Override
//	public UniNamingSender<ByteBuffer> getSendTrapper() {
//		return sendTrapper;
//	}
//	@Override
//	public void setSendTrapper(UniNamingSender<ByteBuffer> newVal) {
//		sendTrapper = newVal;		
//	}

}
