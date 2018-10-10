package port.old;
import java.nio.ByteBuffer;

import extraip.DisconnectRegistrarAndNotifier;
import inputport.ConnectionManager;
import inputport.RemoteEndPointProperties;
import inputport.datacomm.Sender;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;

public interface MonolithicClientInputPort extends ConnectionManager,  Sender<ByteBuffer>, /*UniByteBufferSend,*/ SendRegistrarAndNotifier, PureConnectRegistrarAndNotifier, DisconnectRegistrarAndNotifier, RemoteEndPointProperties {
}
