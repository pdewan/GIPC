package examples.nio.manager.print;

import inputport.nio.manager.listeners.SocketChannelAcceptListener;
import inputport.nio.manager.listeners.SocketChannelReadListener;

public interface NIOManagerPrintServer extends SocketChannelAcceptListener, SocketChannelReadListener {

}
