package examples.threads.counter;

import java.util.ArrayList;
import java.util.List;

public class ACoordinatedCounterNotifyingAll extends ACoordinatedCounter implements CoordinatedCounter {
	
	protected void doNotify() {
		notifyAll();
	}
}
