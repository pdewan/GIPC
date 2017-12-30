package port.old;

import inputport.nio.manager.SocketChannelWriteListener;

public interface MonolithicNIODuplexServerInputPort extends MonolithicNIOServerInputPort, MonolithicDuplexServerInputPort, SocketChannelWriteListener  {

}
