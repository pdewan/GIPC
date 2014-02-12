package port.old;
import inputport.ConnectionManager;
import inputport.RemoteEndPointProperties;
import inputport.datacomm.Sender;
import inputport.datacomm.simplex.buffer.SendRegistrarAndNotifier;

import java.nio.ByteBuffer;

import extraip.DisconnectRegistrarAndNotifier;

public interface MonolithicClientInputPort extends ConnectionManager,  Sender<ByteBuffer>, /*UniByteBufferSend,*/ SendRegistrarAndNotifier, PureConnectRegistrarAndNotifier, DisconnectRegistrarAndNotifier, RemoteEndPointProperties {
}
