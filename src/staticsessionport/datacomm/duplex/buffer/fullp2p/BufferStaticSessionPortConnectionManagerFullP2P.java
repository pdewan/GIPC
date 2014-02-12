package staticsessionport.datacomm.duplex.buffer.fullp2p;

import port.sessionserver.ServerPortDescription;
import multiserverport.datacomm.duplex.buffer.BufferDuplexMultiServerConnectionsManager;

public interface BufferStaticSessionPortConnectionManagerFullP2P extends 
//     DirectBufferBroadcastConnectionsManager, ReceiveListener<ByteBuffer> {
    BufferDuplexMultiServerConnectionsManager {
	//DirectDuplexBufferBroadcastConnectionsManager {
	void createServerInputPort (ServerPortDescription aServerPortDescription);
//	public void connect(ServerPortDescription[] otherServers);

}
