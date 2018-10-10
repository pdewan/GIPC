package inputport.nio.manager;

import java.io.IOException;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import inputport.nio.manager.commands.Request;
import inputport.nio.manager.commands.RequestResponse;
import inputport.nio.manager.commands.Response;
import inputport.nio.manager.listeners.SocketChannelCloseListener;
import util.trace.Tracer;
import util.trace.port.nio.SelectorRequestDequeued;
import util.trace.port.nio.SelectorRequestEnqueued;
import util.trace.port.nio.SelectorWokenUp;



public class ASelectionConnectionManager implements SelectionConnectionManager  {
	Queue<RequestResponse> requestQueue = new ConcurrentLinkedQueue();	

	Map<SelectableChannel, Response> channelToResponse = new HashMap();
//	Map<SelectableChannel, SocketChannelReadListener> channelToReadListener = new HashMap();
	Map<SelectableChannel, SocketChannelCloseListener> channelToCloseListener = new HashMap();
	// why not a single queue
	// why not selectable channel
	SelectionManager selectionManager;
	
	public ASelectionConnectionManager (SelectionManager aSelectionManager) {
		selectionManager = aSelectionManager;
	}
	@Override
	public Response get(SelectableChannel aChannel) {
		return channelToResponse.get(aChannel);
	}
	
	
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionConnectionManager#putRequestResponse(inputport.datacomm.simplex.buffer.nio.RequestResponse)
	 */
	public synchronized void putRequestResponse (RequestResponse request) {	
		Tracer.info(this, "entering synchronized putRequestResponse");
		SelectorRequestEnqueued.newCase(this, request);
		requestQueue.add(request);
		Tracer.info(this, "Waking up selector after adding command:" + request);
		SelectorWokenUp.newCase(this, selectionManager.getSelector());
		selectionManager.getSelector().wakeup();
		Tracer.info(this, "leaving synchronized putRequestResponse");

	}		
	
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionConnectionManager#preProcessConnectAndAccepts()
	 */
	public synchronized void  preProcessConnectAndAccepts() {
		Tracer.info(this, "entering synchronized preProcessConnectAndAccepts");

		// synchronized because while processing, what if new request added
		for (Request request:requestQueue) {
			SelectorRequestDequeued.newCase(this, request);
			Tracer.info(this, "Calling makeRequest on:" + request);
			request.initiate(); 
			Tracer.info(this, "Associating request  with channel to wait for response :" + request.getChannel());
			channelToResponse.put(request.getChannel(), (Response) request);			
		}
		requestQueue.clear();
		Tracer.info(this, "leaving synchronized preProcessConnectAndAccepts");

	}

	
	
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionConnectionManager#close(java.nio.channels.SelectionKey, java.lang.Exception)
	 */
	public void close(SelectionKey selectionKey, Exception readException)  {
		try {
			Tracer.info(this, "Closing and cancelling selection key for:" +  selectionKey.channel());
			selectionKey.channel().close();			
			selectionKey.cancel();
			notifyClose((SocketChannel) selectionKey.channel(), readException);
			return;
		} catch (IOException e) {
			e.printStackTrace();			
		}
	}


	protected void notifyClose(SocketChannel theSocketChannel, Exception readException) {
		SocketChannelCloseListener closeListener = channelToCloseListener.get(theSocketChannel);

		
	}
	
	
	/* (non-Javadoc)
	 * @see inputport.datacomm.simplex.buffer.nio.SelectionConnectionManager#registerCloseListener(java.nio.channels.SelectableChannel, inputport.datacomm.simplex.buffer.nio.SocketChannelCloseListener)
	 */
	public void registerCloseListener(SelectableChannel aChannel, SocketChannelCloseListener aCloseListener) {
		channelToCloseListener.put(aChannel, aCloseListener);		
	}
	
	

}
