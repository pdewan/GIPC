package inputport.datacomm.simplex.buffer.nio;


public interface WriteBoundedBuffer extends Request, Iterable<WriteCommand>{
	boolean isEmpty();
	WriteCommand remove(WriteCommand anElement);
	void put(WriteCommand anElement) throws InterruptedException;
	void addListener(WriteBoundedBufferListener aListener);

}
