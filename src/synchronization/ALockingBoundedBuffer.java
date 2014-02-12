package synchronization;

public class ALockingBoundedBuffer<ElementType> extends ABoundedBufferWithSemaphore<ElementType>{
	Lock lock;
	public ALockingBoundedBuffer(Lock aLock) {
		lock = aLock;
	}
	@Override
	public void put(ElementType element) {
		lock.lock();
		super.put(element);	
		lock.unlock();
	}
	@Override
	public  ElementType get() {
		lock.lock();
		ElementType retVal = super.get();
		lock.unlock();
		return retVal;
	}
}