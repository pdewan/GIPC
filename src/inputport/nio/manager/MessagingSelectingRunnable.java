package inputport.nio.manager;

import inputport.nio.manager.commands.HeaderWriteCommand;


public interface MessagingSelectingRunnable extends SelectionManager {
	void add (HeaderWriteCommand theBufferedHeaderWrite);
}
