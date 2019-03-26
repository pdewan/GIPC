package examples.openmp;

import java.util.Arrays;

public class ReducedForDriver {
	public static void main (String[] args) {
		float[] aFloats = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ForLoops.trace("Single Threaded Case");
		ForLoops.trace(aFloats);
		ForLoops.reducingSum(aFloats);
		ForLoops.trace("Multi Threaded Case");
		float[] aFloatsParallel = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ParallelForLoops.trace(aFloatsParallel);
		ParallelForLoops.reducingSum(aFloatsParallel);
		ParallelForLoops.trace(aFloatsParallel);


	}

}
