package multiserverport.datacomm.group;

import inputport.datacomm.group.AnAbstractGroupInputPort;
import multiserverport.datacomm.duplex.DuplexMultiServerClientPort;

public abstract class AnAbstractGroupMultiServerClientPort<MessageType>  extends 
	AnAbstractGroupInputPort<MessageType> implements GroupMultiServerClientPort<MessageType>  {
	

	protected DuplexMultiServerClientPort<MessageType> duplexServerInputPort;
//	protected ReceiveRegistrarAndNotifier receiveRegistrarAndNotifier = new AReceiveRegistrarAndNotifier();

	public AnAbstractGroupMultiServerClientPort(DuplexMultiServerClientPort<MessageType> aDuplexServerInputPort) {
		super(aDuplexServerInputPort);
		duplexServerInputPort = aDuplexServerInputPort;
//		duplexServerInputPort = aDuplexServerInputPort;
//		duplexServerInputPort.addReceiveListener(this);
	}	

	@Override
	public String getLogicalRemoteEndPoint() {
		return duplexServerInputPort.getLogicalRemoteEndPoint();
	}
	
	@Override
	public String getPhysicalRemoteEndPoint() {
		return duplexServerInputPort.getPhysicalRemoteEndPoint();
	}

	@Override
	public void setPhysicalRemoteEndPoint(String newVal) {
		duplexServerInputPort.setPhysicalRemoteEndPoint(newVal);
		
	}


	@Override
	public void setLogicalRemoteEndPoint(String newVal) {
		duplexServerInputPort.setLogicalRemoteEndPoint(newVal);

		
	}

	@Override
	public boolean isConnected(String remoteName) {
		return duplexServerInputPort.isConnected(remoteName);
	}

//	public void sendOthers(MessageType message) {
//		if (getLastSender() == null) throw new NoMessageReceivedByResponderException();
//		Set<String> peerNames = getConnections();
//		peerNames.remove(getLastSender());
//		send(peerNames, message);
//	}

//
//public Set<String> getConnections() {
//	return duplexServerInputPort.getConnections();
//}





//@Override
//public String getLastSender() {
//	return duplexServerInputPort.getLastSender();
//}
//
//@Override
//public void setLastSender(String newVal) {
//	duplexServerInputPort.setLastSender(newVal);
//	
//}







	



}
