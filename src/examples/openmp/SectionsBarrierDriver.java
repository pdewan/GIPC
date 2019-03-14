package examples.openmp;

import java.util.Arrays;

public class SectionsBarrierDriver {
	public static void main (String[] args) {
		Float[] anObjects = {(float) 4.8, (float) 5.2, (float) 4.5, (float) 4.75, (float) 4.7};
		SumToTextRound.trace("Single Threaded Case");
		SumToTextRound.trace(anObjects);
		SumToTextRound.sectionRoundSumAndToText(anObjects);
		SumToTextRound.trace("Multi Threaded Case");
		Float[] anObjects2 =  {(float) 4.8, (float) 5.2, (float) 4.5, (float) 4.75, (float) 4.7};

		SumToTextRound.trace(anObjects2);
		ParallelSumToTextRound.sectionRoundSumAndToText(anObjects2);
		SumToTextRound.trace(anObjects2);


	}

}
