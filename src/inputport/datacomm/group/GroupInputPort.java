package inputport.datacomm.group;
import inputport.datacomm.duplex.DuplexInputPort;

public interface GroupInputPort<MessageType> extends DuplexInputPort<MessageType>, 
	GroupSender<MessageType>, GroupTrapperSetter<MessageType>{

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
