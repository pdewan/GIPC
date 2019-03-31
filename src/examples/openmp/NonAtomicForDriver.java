package examples.openmp;

import java.util.Arrays;

public class NonAtomicForDriver {
	public static void main (String[] args) {
		float[] aFloats = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ForSumLoops.trace("Single Threaded Case");
		ForSumLoops.trace(aFloats);
		ForSumLoops.nonAtomicSum(aFloats);
		ForSumLoops.trace("Multi Threaded Case");
		float[] aFloatsParallel = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ParallelSumForLoops.trace(aFloatsParallel);
		ParallelSumForLoops.nonAtomicSum(aFloatsParallel);
		ParallelSumForLoops.trace(aFloatsParallel);


	}

}
