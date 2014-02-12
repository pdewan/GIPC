package port.old;
import java.nio.ByteBuffer;
public interface ByteBufferReceiveListener {
	// returning a value creates semantic issues, as there can be more than one listener
	// not making port as an argument as type of port keeps evolving. Better to initialize in constructor.
	// restriction that cannot have a listener for multiple ports, which seems ok.
	public void  messageReceived(String remoteClientName, ByteBuffer message, int length);
	//public void  portClosed(Port localPort, String remoteClientName, boolean explicitClose, String systemMessage);
}
