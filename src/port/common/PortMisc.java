package port.common;

import trace.port.AConnectionEventManager;
import trace.port.ConnectionEventListener;
import trace.port.ConnectiontEventBus;
import bus.uigen.ObjectEditor;

public class PortMisc {
	public static void displayConnections() {
		ConnectionEventListener connectionManager = new AConnectionEventManager();
		ConnectiontEventBus.addConnectionEventListener(connectionManager);
		ObjectEditor.edit(connectionManager);
	}

}
