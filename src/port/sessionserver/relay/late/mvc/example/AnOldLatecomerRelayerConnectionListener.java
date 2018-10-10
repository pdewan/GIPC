package port.sessionserver.relay.late.mvc.example;

import java.util.List;

import inputport.ConnectionListenerWithPort;
import inputport.rpc.duplex.DuplexRPCClientInputPort;
import port.sessionserver.ServerPortDescription;
import port.sessionserver.relay.mvc.example.AnOldRelayerConnectionListener;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public class AnOldLatecomerRelayerConnectionListener extends AnOldRelayerConnectionListener implements LatecomerRelayerConnectionListener {
	List<MessageWithSource> messageList;
	public AnOldLatecomerRelayerConnectionListener(
			ServerPortDescription aServerPortDescription, String aClientName,
			ConnectionListenerWithPort aConnectionListener, List<MessageWithSource> aMessageList) {
		super(aServerPortDescription, aClientName, aConnectionListener);
		messageList = aMessageList;
	}
	public void initMessageList(List<MessageWithSource> aMessageList) {
		messageList = aMessageList;
	}
	@Override
	protected ConnectionListenerWithPort createMVCConnectionListener() {
		return new ALatecomerMVCServerAndLauncherConnectionListener((DuplexRPCClientInputPort) inputPort, messageList);
	}
	
}
