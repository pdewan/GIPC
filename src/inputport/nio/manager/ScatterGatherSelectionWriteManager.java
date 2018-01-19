package inputport.nio.manager;

import inputport.nio.manager.commands.HeaderWriteCommand;


public interface ScatterGatherSelectionWriteManager extends SelectionWriteManager {

	public abstract void add(HeaderWriteCommand theBufferedHeaderWrite);

}