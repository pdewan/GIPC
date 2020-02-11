package examples.rmi.mvc.counter;

import java.util.Scanner;

import examples.mvc.CounterController;
import examples.mvc.local.duplex.Counter;
import util.trace.Tracer;

public class ADistributedCounterController implements CounterController {
	DistributedObservableCounter counter;
	public ADistributedCounterController(DistributedObservableCounter aCounter) {
		counter = aCounter;
	}
	@Override
	public void processInput() {
		Scanner aScanner = new Scanner(System.in);
		while (true) {
			Tracer.info(this, "Increment (or " + QUIT + " ?");
			String aLine = aScanner.nextLine();
			if (QUIT.equals(aLine)) {
				break;
			}
			try {
				counter.increment(Integer.parseInt(aLine));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
