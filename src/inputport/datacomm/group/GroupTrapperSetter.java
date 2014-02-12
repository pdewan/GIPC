package inputport.datacomm.group;

public interface GroupTrapperSetter<MessageType> {
	GroupSendTrapper<MessageType, MessageType> getGroupSendTrapper();
//	String getLastSender();

	void setGroupSendTrapper(
			GroupSendTrapper<MessageType, MessageType> groupSendTrapper);
	
	public GroupToUniSendTrapper<MessageType, MessageType> getGroupToUniSendTrapper() ;
	public void setGroupToUniSendTrapper(
			GroupToUniSendTrapper<MessageType, MessageType> groupToUniSendTrapper) ;
}
