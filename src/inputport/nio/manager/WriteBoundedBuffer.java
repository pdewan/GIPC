package inputport.nio.manager;


public interface WriteBoundedBuffer extends Request, Iterable<WriteCommand>{
	boolean isEmpty();
	WriteCommand remove(WriteCommand anElement);
	void put(WriteCommand anElement) throws InterruptedException;
	void addListener(WriteBoundedBufferListener aListener);

}
