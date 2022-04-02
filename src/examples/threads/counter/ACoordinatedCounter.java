package examples.threads.counter;

import java.util.ArrayList;
import java.util.List;

public class ACoordinatedCounter extends ACounter implements CoordinatedCounter {
	ProducerConsumerTurn turn = ProducerConsumerTurn.PRODUCER_TURN;	
//	List<Object> waits = new ArrayList();
//	ArrayList<Object> notifies = new ArrayList();
	ArrayList<Object> waitsAndNotifies = new ArrayList();

	protected  void waitForConsumerTurn() {
		while (!(turn == ProducerConsumerTurn.CONSUMER_TURN)) {
			try {
//				if (value >= 2)
//					System.out.println (Thread.currentThread() );
//					System.out.println ("C" );

//				System.out.println (Thread.currentThread() + " waiting " + turn);
//				waits.add(Thread.currentThread().getName() + ":" + value);
				
				waitsAndNotifies.add(Thread.currentThread().getName() + ":" + value + " W");

//				waits.add(System.currentTimeMillis());

				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	protected void doNotify() {
		notify();
	}
	
	protected  void waitForProducerTurn() {
		while (!(turn == ProducerConsumerTurn.PRODUCER_TURN)) {
			try {
//				if (value > 2)
//				System.out.println (Thread.currentThread() );
//				System.out.println ("P" );
//				System.out.println (Thread.currentThread() + " waiting " + turn);
//				waits.add(Thread.currentThread());
//				waits.add(Thread.currentThread().getName() + ":" + value);
				waitsAndNotifies.add(Thread.currentThread().getName() + ":" + value + " W");

//				waits.add(System.currentTimeMillis());

				if (value == 3) {
//					System.out.println("Waits" + waits);
					System.out.println("notifies" + waitsAndNotifies);

				}

				wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	public synchronized int getValue() {
		waitForConsumerTurn();
		int result = super.getValue();
		turn = ProducerConsumerTurn.PRODUCER_TURN;
//		System.out.println (Thread.currentThread() + " notifying " + turn);
		doNotify();	
		waitsAndNotifies.add(Thread.currentThread().getName() + " " + value + " N");
//		notifies.add(System.currentTimeMillis());

		return result;
	}
	public synchronized void increment() {
		waitForProducerTurn();
		super.increment();
		turn = ProducerConsumerTurn.CONSUMER_TURN;
//		System.out.println (Thread.currentThread() + " notifying " + turn);
		
		doNotify();	
		waitsAndNotifies.add(Thread.currentThread().getName() + " " + value + " N");

//		notifies.add(System.currentTimeMillis());

//		if (value == 3) {
////			System.out.println("Waits" + waits);
//			System.out.println("notifies" + waitsAndNotifies);
//
//		}
//notifies[Producer 1 1 N, Producer 1:1 W, Consumer 2 1 N, Consumer 2:1W , Consumer 1:1W , Producer 2 2 N, Producer 2:2 W, Consumer 2 2 N, Consumer 2:2W , Consumer 1:2W , Producer 1 3 N]

	}
}
