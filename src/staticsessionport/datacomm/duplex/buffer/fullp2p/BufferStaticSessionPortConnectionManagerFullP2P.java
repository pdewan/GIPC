package staticsessionport.datacomm.duplex.buffer.fullp2p;

import multiserverport.datacomm.duplex.buffer.BufferDuplexMultiServerConnectionsManager;
import port.sessionserver.ServerPortDescription;

public interface BufferStaticSessionPortConnectionManagerFullP2P extends 
//     DirectBufferBroadcastConnectionsManager, ReceiveListener<ByteBuffer> {
    BufferDuplexMultiServerConnectionsManager {
	//DirectDuplexBufferBroadcastConnectionsManager {
	void createServerInputPort (ServerPortDescription aServerPortDescription);
//	public void connect(ServerPortDescription[] otherServers);

}
