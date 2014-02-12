package oldrpcip;

public interface SerializableCallReceiver {

	public void messageReceived(String remoteClientName,
			SerializableCall message);

}