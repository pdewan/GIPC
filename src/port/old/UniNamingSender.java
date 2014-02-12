package port.old;

import java.nio.ByteBuffer;

public interface UniNamingSender {
	void send(String remoteName, ByteBuffer message);
}
