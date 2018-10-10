package port.common;

import bus.uigen.ObjectEditor;
import util.trace.port.AConnectionEventManager;
import util.trace.port.ConnectionEventListener;
import util.trace.port.ConnectiontEventBus;

public class PortMisc {
	public static void displayConnections() {
		ConnectionEventListener connectionManager = new AConnectionEventManager();
		ConnectiontEventBus.addConnectionEventListener(connectionManager);
		ObjectEditor.edit(connectionManager);
	}

}
