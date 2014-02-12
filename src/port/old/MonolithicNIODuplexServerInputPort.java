package port.old;

import inputport.datacomm.simplex.buffer.nio.SocketChannelWriteListener;

public interface MonolithicNIODuplexServerInputPort extends MonolithicNIOServerInputPort, MonolithicDuplexServerInputPort, SocketChannelWriteListener  {

}
