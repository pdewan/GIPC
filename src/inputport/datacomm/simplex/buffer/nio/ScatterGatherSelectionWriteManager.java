package inputport.datacomm.simplex.buffer.nio;

public interface ScatterGatherSelectionWriteManager extends SelectionWriteManager {

	public abstract void add(HeaderWriteCommand theBufferedHeaderWrite);

}