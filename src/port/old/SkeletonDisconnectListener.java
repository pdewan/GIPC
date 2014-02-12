package port.old;
public interface SkeletonDisconnectListener<MessageChannelType> {	
	public void  disconnected(MessageChannelType aRemoteEnd, boolean anExplicitDsconnection, String anExplanation);
}
