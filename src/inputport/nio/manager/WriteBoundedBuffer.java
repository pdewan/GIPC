package inputport.nio.manager;

import inputport.nio.manager.commands.Request;
import inputport.nio.manager.commands.WriteCommand;
import inputport.nio.manager.listeners.WriteBoundedBufferListener;


public interface WriteBoundedBuffer extends Request, Iterable<WriteCommand>{
	boolean isEmpty();
	WriteCommand remove(WriteCommand anElement);
	void put(WriteCommand anElement) throws InterruptedException;
	void addListener(WriteBoundedBufferListener aListener);

}
