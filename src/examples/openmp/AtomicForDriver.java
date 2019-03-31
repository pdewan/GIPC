package examples.openmp;

import java.util.Arrays;

public class AtomicForDriver {
	public static void main (String[] args) {
		float[] aFloats = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ForSumLoops.trace("Single Threaded Case");
		ForSumLoops.trace(aFloats);
		ForSumLoops.atomicSum(aFloats);
		ForSumLoops.trace("Multi Threaded Case");
		float[] aFloatsParallel = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ParallelSumForLoops.trace(aFloatsParallel);
		ParallelSumForLoops.atomicSum(aFloatsParallel);
		ParallelSumForLoops.trace(aFloatsParallel);


	}

}
