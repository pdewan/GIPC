package inputport.nio.manager;

public interface MessagingSelectingRunnable extends SelectionManager {
	void add (HeaderWriteCommand theBufferedHeaderWrite);
}
