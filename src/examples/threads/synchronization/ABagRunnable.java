package examples.threads.synchronization;

public class ABagRunnable<ElementType> implements Runnable {
	Bag<ElementType> bag;
	ElementType element;
	public ABagRunnable(Bag<ElementType> aBag, ElementType anElement) {
		bag = aBag;
		element = anElement;
	}
	@Override
	public void run() {
		bag.put(element);
	}

}
