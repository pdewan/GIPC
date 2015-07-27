package inputport.datacomm.simplex.object;

import java.nio.ByteBuffer;

import inputport.datacomm.ReceiveTrapper;

public interface DeserializingForwarder extends ReceiveTrapper<ByteBuffer, Object> {

}
