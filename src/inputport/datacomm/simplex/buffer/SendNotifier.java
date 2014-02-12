package inputport.datacomm.simplex.buffer;

import java.nio.ByteBuffer;

public interface SendNotifier {
	void notifyPortSend(String aRemoteEnd, ByteBuffer message, int sendId);
}
