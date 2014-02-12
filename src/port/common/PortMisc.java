package port.common;

import port.trace.AConnectionEventManager;
import port.trace.ConnectionEventListener;
import port.trace.ConnectiontEventBus;
import bus.uigen.ObjectEditor;

public class PortMisc {
	public static void displayConnections() {
		ConnectionEventListener connectionManager = new AConnectionEventManager();
		ConnectiontEventBus.addConnectionEventListener(connectionManager);
		ObjectEditor.edit(connectionManager);
	}

}
