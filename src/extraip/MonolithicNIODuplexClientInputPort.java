package extraip;

import inputport.nio.manager.SocketChannelReadListener;
import port.old.MonolithicDuplexClientInputPort;
import port.old.MonolithicNIOClientInputPort;

public interface MonolithicNIODuplexClientInputPort extends MonolithicDuplexClientInputPort, MonolithicNIOClientInputPort, SocketChannelReadListener{

}
