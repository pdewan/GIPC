package inputport.datacomm.simplex.buffer;
import java.nio.ByteBuffer;
public interface ByteBufferSendListener {
	// returning a value creates semantic issues, as there can be more than one listener
	// not making port as an argument as type of port keeps evolving. Better to initialize in constructor.
	// restriction that cannot have a listener for multiple ports, which seems ok.
	public void  messageSent(String aRemoteEnd, ByteBuffer message, int sendId);
}
