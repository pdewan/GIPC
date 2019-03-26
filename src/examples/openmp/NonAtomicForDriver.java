package examples.openmp;

import java.util.Arrays;

public class NonAtomicForDriver {
	public static void main (String[] args) {
		float[] aFloats = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ForLoops.trace("Single Threaded Case");
		ForLoops.trace(aFloats);
		ForLoops.nonAtomicSum(aFloats);
		ForLoops.trace("Multi Threaded Case");
		float[] aFloatsParallel = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ParallelForLoops.trace(aFloatsParallel);
		ParallelForLoops.nonAtomicSum(aFloatsParallel);
		ParallelForLoops.trace(aFloatsParallel);


	}

}
