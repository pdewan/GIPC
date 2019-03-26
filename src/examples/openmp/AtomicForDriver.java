package examples.openmp;

import java.util.Arrays;

public class AtomicForDriver {
	public static void main (String[] args) {
		float[] aFloats = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ForLoops.trace("Single Threaded Case");
		ForLoops.trace(aFloats);
		ForLoops.atomicSum(aFloats);
		ForLoops.trace("Multi Threaded Case");
		float[] aFloatsParallel = {(float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0, (float) 5.0};
		ParallelForLoops.trace(aFloatsParallel);
		ParallelForLoops.atomicSum(aFloatsParallel);
		ParallelForLoops.trace(aFloatsParallel);


	}

}
