package examples.threads;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeSupport;

import examples.mvc.AnObservableCounter;
import examples.threads.synchronization.ABoundedBuffer;
import examples.threads.synchronization.BoundedBuffer;
import util.trace.Tracer;

public class AnAsynchronousCounter extends AnObservableCounter implements AsynchronousCounter {
	protected CircularBoundedBuffer<PropertyChangeEvent> propertyChangeEvents = new ACircularBoundedBuffer();
	protected PropertyChangeEvent lastPropertyChangeEvent;
	public AnAsynchronousCounter() {
		Thread aNotifyingThread = new Thread(this);
		aNotifyingThread.setName(NOTIFYING_THREAD);
		aNotifyingThread.start();
	}

	@Override
//	public synchronized void run() {
	public void run() {
		while (true) {
			Tracer.info(this, "Started wait for next property change event");			
//			try {
//				wait();
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//			Tracer.info(this, "Ended wait");
			PropertyChangeEvent aPropertyChangeEvent = propertyChangeEvents.get();			
			propertyChangeSupport.firePropertyChange(aPropertyChangeEvent);			
		}		
	}
//	protected synchronized void processPropertyChangeEvent(PropertyChangeEvent aPropertyChangeEvent) {		
	protected void processPropertyChangeEvent(PropertyChangeEvent aPropertyChangeEvent) {
//		lastPropertyChangeEvent = aPropertyChangeEvent;
		propertyChangeEvents.put(aPropertyChangeEvent);		
//		notify()
	}

}
