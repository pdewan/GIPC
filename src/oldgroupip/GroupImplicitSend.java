package oldgroupip;


import java.nio.ByteBuffer;

import port.old.DuplexServerSend;


public interface GroupImplicitSend extends DuplexServerSend {
	public void sendAll(ByteBuffer message);
	public void sendOthers(ByteBuffer message);
}
