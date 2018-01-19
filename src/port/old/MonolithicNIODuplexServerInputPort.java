package port.old;

import inputport.nio.manager.listeners.SocketChannelWriteListener;

public interface MonolithicNIODuplexServerInputPort extends MonolithicNIOServerInputPort, MonolithicDuplexServerInputPort, SocketChannelWriteListener  {

}
