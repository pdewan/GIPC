package inputport.datacomm.simplex.buffer.nio;




public class AScatterGatherSelectionManager extends ASelectionManager implements MessagingSelectingRunnable  {
	public static final int BYTES_IN_INT =  Integer.SIZE/Byte.SIZE;
//	BlockingQueue<HeaderWriteCommand> headerBufferPool = new ArrayBlockingQueue<HeaderWriteCommand>(AScatterGatherSelectionManager.MAX_OUTSTANDING_WRITES);


//	int messageLength;
//	public static final int MAX_OUTSTANDING_WRITES = 256*4*4; // 4K buffers should be enough for even mouse movements, but with custom serializers get heap space issues
	
	private static  int maxOutstandingWrites = 10; // with custom seralizers 


	protected SelectionReadManager createSelectionReadManager() {
		return new AScatterGatherSelectionReadManager(this);
	}
	protected SelectionWriteManager createSelectionWriteManager() {
		return new AScatterGatherSelectionWriteManager(this);
	}
	@Override
	public void add(HeaderWriteCommand theBufferedHeaderWrite) {
		((ScatterGatherSelectionWriteManager) writeManager).add(theBufferedHeaderWrite);
	}
	public static int getMaxOutstandingWrites() {
		return maxOutstandingWrites;
	}
	public static void setMaxOutstandingWrites(int mAX_OUTSTANDING_WRITES) {
		maxOutstandingWrites = mAX_OUTSTANDING_WRITES;
	}
	
//	@Override
//	protected ReadCommand createReadHandler(SocketChannel theSocketChannel) {
//		return new AScatterGatherReadCommand(this, theSocketChannel);
//	}
//	public AScatterGatherSelectionManager() {
//		allocateHeaderBuffers();
//	}	
//	void allocateHeaderBuffers() {
//		Tracer.info(this, "Allocating resuable header buffers for message sizes");
//		for (int i = 0; i < AScatterGatherSelectionManager.MAX_OUTSTANDING_WRITES; i++) {
//			add(new AHeaderWriteCommand(this, null));
//		}
//	}
//	public void add (HeaderWriteCommand theBufferedHeaderWrite) {
//		try {
////			Tracer.info(this, "Putting header buffer back " + theBufferedHeaderWrite );
//			headerBufferPool.put(theBufferedHeaderWrite);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	@Override
//	public synchronized void putBufferedWrite (WriteCommand bufferedWrite) {
//		try {
//			Tracer.info(this, "entering synchronized putBufferedWrite");
//
//			// called by client, so can block
//			if (headerBufferPool.isEmpty()) {
//				Tracer.error("Exhausted header buffer pool. Not blocking as it will lock out selectionmanager. Ignoring write:" + bufferedWrite);
//				return;
//			}
//			// to bad blocking monitor call in another monitor does not release original monitor
////			HeaderWriteCommand headerBufferedWrite = headerBufferPool.take();
//			// no blocking
//			HeaderWriteCommand headerBufferedWrite = headerBufferPool.remove();
//			Tracer.info(this, "Took header buffer:" + bufferedWrite);
//			headerBufferedWrite.makeHeaderFor(bufferedWrite);
//			WriteBoundedBuffer bufferedWriteBoundedBuffer = getBufferedWriteBoundedBuffer(bufferedWrite);
//			Tracer.info(this, "Qeuing with selector header:" +  headerBufferedWrite);			
//			bufferedWriteBoundedBuffer.put(headerBufferedWrite);
//			Tracer.info(this, "Finished Qeuing with selector header:" +  headerBufferedWrite);
//			super.putBufferedWrite(bufferedWrite)	;	
//			Tracer.info(this, "leaving synchronized putBufferedWrite");
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
	

    




}
