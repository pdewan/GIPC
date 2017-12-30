package inputport.nio.manager;

public interface ScatterGatherSelectionWriteManager extends SelectionWriteManager {

	public abstract void add(HeaderWriteCommand theBufferedHeaderWrite);

}