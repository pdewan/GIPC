package inputport.datacomm.simplex.object;

import java.nio.ByteBuffer;

import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;

public interface SerializingForwarder extends SendTrapper<Object, ByteBuffer> {

}
