package inputport.nio;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;



public class ASelectionReadManager implements SelectionReadManager  {
	Map<SocketChannel, ReadCommand> channelToReadHandler = new HashMap();
	SelectionManager selectionManager;	
	public ASelectionReadManager (SelectionManager aSelectionManager) {
		selectionManager = aSelectionManager;
	}
	
	protected ReadCommand createReadHandler(SocketChannel theSocketChannel) {
		return new AReadCommand(selectionManager, theSocketChannel);
	}
	
	protected void allocateReadState(SocketChannel theSocketChannel) {
		ReadCommand readHandler = createReadHandler(theSocketChannel);
		channelToReadHandler.put(theSocketChannel, readHandler);
	
		
	}
	@Override
	public ReadCommand getReadHandler(SocketChannel theSocketChannel) {
		ReadCommand readVal = channelToReadHandler.get(theSocketChannel);
		if (readVal == null) 
			allocateReadState(theSocketChannel);
		return channelToReadHandler.get(theSocketChannel);			
	}
	
	
			
	


	
	@Override
	public boolean processRead(SelectionKey theSelectionKey) /*throws IOException*/ {
		
		SocketChannel socketChannel = (SocketChannel) theSelectionKey.channel();
		return getReadHandler(socketChannel).execute();

	}
	
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionReadManager#registerReadListener(java.nio.channels.SocketChannel, inputport.datacomm.simplex.buffer.nio.SocketChannelReadListener)
	 */
	public void registerReadListener(SocketChannel theChannel, SocketChannelReadListener theReadListener) {
		getReadHandler(theChannel).addReadListener(theReadListener);
	}
	
	

}
