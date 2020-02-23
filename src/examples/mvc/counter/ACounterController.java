package examples.mvc.counter;

import java.util.Scanner;

import examples.counter.Counter;
import util.trace.Tracer;

public class ACounterController implements CounterController {
	Counter counter;
	public ACounterController(Counter aCounter) {
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
