package examples.nio;

import inputport.nio.SocketChannelAcceptListener;
import inputport.nio.SocketChannelReadListener;


public interface MeaningOfLifeNIOServer extends ServerPort, SocketChannelReadListener, SocketChannelAcceptListener {

}
