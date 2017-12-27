package port.old;

import inputport.nio.SocketChannelWriteListener;

public interface MonolithicNIODuplexServerInputPort extends MonolithicNIOServerInputPort, MonolithicDuplexServerInputPort, SocketChannelWriteListener  {

}
