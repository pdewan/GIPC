package inputport.nio.manager;

import inputport.nio.manager.commands.HeaderWriteCommand;
import inputport.nio.manager.commands.WriteCommand;
import inputport.nio.manager.commands.classes.AHeaderWriteCommand;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import util.trace.Tracer;



public class AScatterGatherSelectionWriteManager extends ASelectionWriteManager implements ScatterGatherSelectionWriteManager   {
//	public static final int BYTES_IN_INT =  Integer.SIZE/Byte.SIZE;
	BlockingQueue<HeaderWriteCommand> headerBufferPool = new ArrayBlockingQueue<HeaderWriteCommand>(AScatterGatherSelectionWriteManager.MAX_OUTSTANDING_WRITES);


	int messageLength;
	public static final int MAX_OUTSTANDING_WRITES = 256*4*4; // 4K buffers should be enough for even mouse movements

	public AScatterGatherSelectionWriteManager (MessagingSelectingRunnable aSelectionManager) {
		super(aSelectionManager);
		allocateHeaderBuffers();
	}
	
	void allocateHeaderBuffers() {
		Tracer.info(this, "Allocating resuable header buffers for message sizes");
		for (int i = 0; i < AScatterGatherSelectionWriteManager.MAX_OUTSTANDING_WRITES; i++) {
			add(new AHeaderWriteCommand((MessagingSelectingRunnable) selectionManager, null));
		}
	}
	@Override
	public void add (HeaderWriteCommand theBufferedHeaderWrite) {
		try {
//			Tracer.info(this, "Putting header buffer back " + theBufferedHeaderWrite );
			headerBufferPool.put(theBufferedHeaderWrite);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	@Override
	public synchronized void putBufferedWrite (WriteCommand bufferedWrite) {
		try {
			Tracer.info(this, "entering synchronized putBufferedWrite");

			// called by client, so can block
			if (headerBufferPool.isEmpty()) {
				Tracer.error("Exhausted header buffer pool. Not blocking as it will lock out selectionmanager. Ignoring write:" + bufferedWrite);
				return;
			}
			// to bad blocking monitor call in another monitor does not release original monitor
//			HeaderWriteCommand headerBufferedWrite = headerBufferPool.take();
			// no blocking
			HeaderWriteCommand headerBufferedWrite = headerBufferPool.remove();
			Tracer.info(this, "Took header buffer:" + bufferedWrite);
			headerBufferedWrite.makeHeaderFor(bufferedWrite);
			WriteBoundedBuffer bufferedWriteBoundedBuffer = getBufferedWriteBoundedBuffer(bufferedWrite);
			Tracer.info(this, "Qeuing with selector header:" +  headerBufferedWrite);			
			bufferedWriteBoundedBuffer.put(headerBufferedWrite);
			Tracer.info(this, "Finished Qeuing with selector header:" +  headerBufferedWrite);
			super.putBufferedWrite(bufferedWrite)	;	
			Tracer.info(this, "leaving synchronized putBufferedWrite");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	






}
