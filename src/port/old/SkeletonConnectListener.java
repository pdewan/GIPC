package port.old;

public interface SkeletonConnectListener<MessageChannel> {
	public void connected(MessageChannel aRemoteEnd);
	public void notConnected(MessageChannel aRemoteEnd, String anExplanation);
}
