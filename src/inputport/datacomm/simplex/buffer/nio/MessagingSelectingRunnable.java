package inputport.datacomm.simplex.buffer.nio;

public interface MessagingSelectingRunnable extends SelectionManager {
	void add (HeaderWriteCommand theBufferedHeaderWrite);
}
