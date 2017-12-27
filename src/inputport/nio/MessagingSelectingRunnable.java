package inputport.nio;

public interface MessagingSelectingRunnable extends SelectionManager {
	void add (HeaderWriteCommand theBufferedHeaderWrite);
}
