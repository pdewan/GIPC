package inputport.nio;

public interface ScatterGatherSelectionWriteManager extends SelectionWriteManager {

	public abstract void add(HeaderWriteCommand theBufferedHeaderWrite);

}