package examples.threads.counter;

import java.util.ArrayList;
import java.util.List;

public class ACoordinatedCounterNotifyingWait extends ACoordinatedCounter implements CoordinatedCounter {
	protected  void waitForConsumerTurn() {
		boolean hasWaited = false;
		while (!(turn == ProducerConsumerTurn.CONSUMER_TURN)) {
			try {	
				if (hasWaited) {
					notify();
				}
				wait();
				hasWaited = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}	
	protected void waitForProducerTurn() {
		boolean hasWaited = false;
		while (!(turn == ProducerConsumerTurn.PRODUCER_TURN)) {
			try {
				if (hasWaited) {
					notify();
				}
				wait();
				hasWaited = true;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
