package inputport.nio.manager;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import trace.port.nio.SelectorWokenUp;
import util.trace.Tracer;



public class ASelectionWriteManager implements SelectionWriteManager  {
	protected List<WriteCommand> nextBufferedWrites = new ArrayList();
//	Selector selector ;
	protected SelectionManager selectionManager;
	
	protected Map<SocketChannel, WriteBoundedBuffer> channelToBufferedWriteBoundedBuffer = new Hashtable();	

	
	public ASelectionWriteManager (SelectionManager aSelectionManager) {
//		selector = aSelector;
		selectionManager = aSelectionManager;
	}
	@Override
	public WriteBoundedBuffer get (SocketChannel aSocketChannel) {
		return channelToBufferedWriteBoundedBuffer.get(aSocketChannel);
	}
	
	
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionWriteManager#getBufferedWriteBoundedBuffer(inputport.datacomm.simplex.buffer.nio.WriteCommand)
	 */
	public WriteBoundedBuffer getBufferedWriteBoundedBuffer(WriteCommand bufferedWrite) {

		SocketChannel channel = bufferedWrite.getSocketChannel();
		return getBufferedWriteBoundedBuffer(channel);
//		WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(channel);
//
//		if (bufferedWriteBoundedBuffer == null) {
//			bufferedWriteBoundedBuffer =  new AWriteBoundedBuffer(selectionManager, channel);
//
//			channelToBufferedWriteBoundedBuffer.put(channel, bufferedWriteBoundedBuffer);
//		}
//		return bufferedWriteBoundedBuffer;
	}
	@Override
	public WriteBoundedBuffer getBufferedWriteBoundedBuffer(SocketChannel channel) {

		WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(channel);

		if (bufferedWriteBoundedBuffer == null) {
			bufferedWriteBoundedBuffer =  new AWriteBoundedBuffer(selectionManager, channel);

			channelToBufferedWriteBoundedBuffer.put(channel, bufferedWriteBoundedBuffer);
		}
		return bufferedWriteBoundedBuffer;
	}
	
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionWriteManager#putBufferedWrite(inputport.datacomm.simplex.buffer.nio.WriteCommand)
	 */
	public synchronized void putBufferedWrite (WriteCommand bufferedWrite) {
		try {
			Tracer.info(this, "Started storing of buffered write with id:" + bufferedWrite.getId());
			WriteBoundedBuffer bufferedWriteBoundedBuffer = getBufferedWriteBoundedBuffer(bufferedWrite);

			bufferedWriteBoundedBuffer.put(bufferedWrite);	
			if (registerWriteOpsForBufferedWrites(bufferedWrite)) {
				Tracer.info(this, "Waking up selector as new write registered");
				SelectorWokenUp.newCase(this, selectionManager.getSelector());
				selectionManager.getSelector().wakeup();
			}
//			Tracer.info(this, "Finished storing of buffered write with id:" + bufferedWrite.getId());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}	
	
	

	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionWriteManager#processBufferedWrites()
	 */
	public void processBufferedWrites() {
		for (WriteBoundedBuffer bufferedWriteBoundedBuffer: channelToBufferedWriteBoundedBuffer.values()) {

			processChannelBufferedWrites(bufferedWriteBoundedBuffer);

			
		}
	}
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionWriteManager#registerWriteOpsForBufferedWrites(inputport.datacomm.simplex.buffer.nio.WriteCommand)
	 */
	public boolean registerWriteOpsForBufferedWrites(WriteCommand writeBuffer) {
		return makeRequestForWriteBoundedBuffer(writeBuffer.getSocketChannel());
	}
//	@Override
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionWriteManager#makeRequestForWriteBoundedBuffer(java.nio.channels.SocketChannel)
	 */
	public boolean makeRequestForWriteBoundedBuffer(SocketChannel channel) {
		WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(channel);
		return bufferedWriteBoundedBuffer.initiate();

		
	}
	 // keep getting concurrent change exception without synchronized
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionWriteManager#preProcessBufferedWrites()
	 */
	public synchronized void  preProcessBufferedWrites() {
		Tracer.info(this, "entering synchronized preProcessBufferedWrites");


		for (SocketChannel channel: channelToBufferedWriteBoundedBuffer.keySet()) {
			if (!makeRequestForWriteBoundedBuffer(channel))
				Tracer.info(this, "channel not connected or no pending writes:" + channel);
		}	
		
		Tracer.info(this, "leaving synchronized preProcessBufferedWrites");

	}

	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionWriteManager#processChannelBufferedWrites(java.nio.channels.SelectionKey)
	 */
	public void  processChannelBufferedWrites(SelectionKey selectionKey) {	
		WriteBoundedBuffer bufferedWriteBoundedBuffer = channelToBufferedWriteBoundedBuffer.get(selectionKey.channel());
		// could the bounded buffer be null?
		processChannelBufferedWrites(bufferedWriteBoundedBuffer);		
	}
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionWriteManager#processChannelBufferedWrites(inputport.datacomm.simplex.buffer.nio.WriteBoundedBuffer)
	 */
	public void  processChannelBufferedWrites(WriteBoundedBuffer bufferedWriteBoundedBuffer) {	
		for (WriteCommand bufferedWrite: bufferedWriteBoundedBuffer) {
			if (bufferedWrite.execute())
				bufferedWriteBoundedBuffer.remove(bufferedWrite);
			else //  break out of inner loop as the last bufferedWrite could not finish
				break;
		}		
	}	

}
