package port.sessionserver.relay.late.mvc.dynamic.example;

import port.sessionserver.SessionObserver;
import port.sessionserver.relay.late.mvc.example.LatecomerRelayerConnectionListener;
import port.sessionserver.relay.mvc.example.ServerConnectingConnectionListener;

public interface DynamicServerSessionObserver extends SessionObserver, LatecomerRelayerConnectionListener {

}
