package inputport.nio.manager.factories;

import inputport.nio.manager.AScatterGatherSelectionManager;
import inputport.nio.manager.SelectionManager;

public class SelectionManagerFactory  {
	public static String SELECTING_THREAD_NAME = "Selecting Thread";
	protected static SelectionManager selectionManager;
	public static SelectionManager getSelectionManager() {
		if (selectionManager == null) {
			selectionManager = new AScatterGatherSelectionManager();
			Thread selectingThread = new Thread (selectionManager);
			selectingThread.setName(SELECTING_THREAD_NAME);
			selectingThread.start();
		}
		return selectionManager;
	}	
}
