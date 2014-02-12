package port.trace;

import java.util.HashSet;
import java.util.Set;

// this bus was created before the more general TracerBus, so gluing the two together
public class ConnectiontEventBus {
	static Set<ConnectionEventListener> listeners = new HashSet();
	
	public static void newEvent(ConnectionEvent anEvent) {
		notifyConnectionListeners(anEvent);
	}
	
	public static void receiveOnlySource(Object aSource) {
		ReceiveOnlySource.newCase(aSource);
		notifyReceiveOnlySource(aSource);
	}
	
	public static void sendOnlySource(Object aSource) {
		SendOnlySource.newCase(aSource);
		notifySendOnlySource(aSource);
	}
	
	public static void newEvent(ReplaceConnectionEvent anEvent) {
		notifyConnectionListeners(anEvent);
	}
	
	
	public static void addConnectionEventListener(ConnectionEventListener aListener) {
		listeners.add(aListener);
	}
	
	public static void removeConnectionEventListener(ConnectionEventListener aListener) {
		listeners.remove(aListener);
	}
	
	 static void notifyConnectionListeners(ConnectionEvent anEvent) {
		for (ConnectionEventListener aBusListener:listeners) {
			aBusListener.newEvent(anEvent);
		}
	}
	 
	 static void notifyConnectionListeners(ReplaceConnectionEvent anEvent) {
			for (ConnectionEventListener aBusListener:listeners) {
				aBusListener.newEvent(anEvent);
			}
		}
	 static void notifySendOnlySource(Object aSource) {
			for (ConnectionEventListener aBusListener:listeners) {
				aBusListener.sendOnlySource(aSource);
			}
		}
	 static void notifyReceiveOnlySource(Object aSource) {
			for (ConnectionEventListener aBusListener:listeners) {
				aBusListener.receiveOnlySource(aSource);
			}
		}
//	static {
//		DistEventsBus.addConnectionEventListener(new AConnectionEventManager());
//	}

}
