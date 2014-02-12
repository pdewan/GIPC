package inputport.datacomm.group;

import inputport.datacomm.duplex.DuplexServerInputPort;





public abstract class AnAbstractGroupServerInputPort<MessageType>  extends 
	AnAbstractGroupInputPort<MessageType>  implements GroupServerInputPort<MessageType> {
	

	protected DuplexServerInputPort<MessageType> duplexServerInputPort;

	public AnAbstractGroupServerInputPort(DuplexServerInputPort<MessageType> aDuplexServerInputPort) {
		super(aDuplexServerInputPort);
		duplexServerInputPort = aDuplexServerInputPort;

	}	

	






@Override
public String getServerId() {
	return duplexServerInputPort.getServerId();
}

@Override
public boolean isConnected(String remoteName) {
	// TODO Auto-generated method stub
	return duplexServerInputPort.isConnected(remoteName);
}


//@Override
//public SendTrapper<MessageType, MessageType> getSendTrapper() {
//	return duplexServerInputPort.getSendTrapper();
//}
//@Override
//public void setSendTrapper(SendTrapper<MessageType, MessageType> newVal) {
//	duplexServerInputPort.setSendTrapper(newVal);
//}
//@Override
//public GroupSendTrapper<MessageType, MessageType> getGroupSendTrapper() {
//	return groupSendTrapper;
//}
//@Override
//public void setGroupSendTrapper(
//		GroupSendTrapper<MessageType, MessageType> groupSendTrapper) {
//	this.groupSendTrapper = groupSendTrapper;
////	groupSendTrapper.setDestination(groupToUniSendTrapper);
//}
//@Override
//public GroupToUniSendTrapper<MessageType, MessageType> getGroupToUniSendTrapper() {
//	return groupToUniSendTrapper;
//}
//@Override
//public void setGroupToUniSendTrapper(
//		GroupToUniSendTrapper<MessageType, MessageType> groupToUniSendTrapper) {
//	this.groupToUniSendTrapper = groupToUniSendTrapper;
//}

	



}
