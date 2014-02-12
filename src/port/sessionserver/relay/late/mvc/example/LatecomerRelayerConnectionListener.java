package port.sessionserver.relay.late.mvc.example;

import java.util.List;

import port.sessionserver.relay.mvc.example.ServerConnectingConnectionListener;
import sessionport.datacomm.duplex.object.relayed.MessageWithSource;

public interface LatecomerRelayerConnectionListener extends ServerConnectingConnectionListener{
	public void initMessageList(List<MessageWithSource> aMessageList);
}
