package inputport.datacomm.duplex.buffer;

import java.nio.ByteBuffer;

import inputport.datacomm.ReceiveListener;
import inputport.datacomm.duplex.DuplexInputPort;

public interface DuplexBufferInputPort extends DuplexInputPort<ByteBuffer> , ReceiveListener<ByteBuffer>{

}
