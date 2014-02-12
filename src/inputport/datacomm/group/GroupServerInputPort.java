package inputport.datacomm.group;
import inputport.datacomm.duplex.DuplexServerInputPort;

public interface GroupServerInputPort<MessageType> extends DuplexServerInputPort<MessageType>, 
	GroupInputPort<MessageType>{

//	GroupSendTrapper<MessageType, MessageType> getGroupSendTrapper();
////	String getLastSender();
//
//	void setGroupSendTrapper(
//			GroupSendTrapper<MessageType, MessageType> groupSendTrapper);
//	
//	public GroupToUniSendTrapper<MessageType, MessageType> getGroupToUniSendTrapper() ;
//	public void setGroupToUniSendTrapper(
//			GroupToUniSendTrapper<MessageType, MessageType> groupToUniSendTrapper) ;

}
