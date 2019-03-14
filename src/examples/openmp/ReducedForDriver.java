package examples.openmp;

import java.util.Arrays;

public class ReducedForDriver {
	public static void main (String[] args) {
		Float[] anObjects = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ForLoops.trace("Single Threaded Case");
		ForLoops.trace(anObjects);
		ForLoops.reducingSum(anObjects);
		ForLoops.trace("Multi Threaded Case");
		Float[] anObjects2 = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ParallelForLoops.trace(anObjects2);
		ParallelForLoops.reducingSum(anObjects2);
		ParallelForLoops.trace(anObjects2);


	}

}
