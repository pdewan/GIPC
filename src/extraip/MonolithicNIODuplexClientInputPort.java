package extraip;

import inputport.datacomm.simplex.buffer.nio.SocketChannelReadListener;
import port.old.MonolithicDuplexClientInputPort;
import port.old.MonolithicNIOClientInputPort;

public interface MonolithicNIODuplexClientInputPort extends MonolithicDuplexClientInputPort, MonolithicNIOClientInputPort, SocketChannelReadListener{

}
