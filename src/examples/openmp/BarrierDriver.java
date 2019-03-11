package examples.openmp;

import java.util.Arrays;

public class BarrierDriver {
	public static void main (String[] args) {
		Float[] anObjects = {(float) 4.8, (float) 5.2, (float) 4.5, (float) 4.75, (float) 4.7};
		BarrierExample.trace("Single Threaded Case");
		BarrierExample.trace(anObjects);
		BarrierExample.barrierRoundSumAndToText(anObjects);
		BarrierExample.trace("Multi Threaded Case");
		Float[] anObjects2 =  {(float) 4.8, (float) 5.2, (float) 4.5, (float) 4.75, (float) 4.7};

		BarrierExample.trace(anObjects2);
		ParallelBarrierExample.roundSumAndToText(anObjects2);
		BarrierExample.trace(anObjects2);


	}

}
