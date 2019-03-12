package examples.openmp;

import java.util.Arrays;

public class SectionsDriver {
	public static void main (String[] args) {
		Float[] anObjects = {(float) 4.8, (float) 5.2, (float) 4.5, (float) 4.75, (float) 4.7};
		SumToTextRoundExamples.trace("Single Threaded Case");
		SumToTextRoundExamples.trace(anObjects);
		SumToTextRoundExamples.sectionSumAndToText(anObjects);
		SumToTextRoundExamples.trace("Multi Threaded Case");
		Float[] anObjects2 =  {(float) 4.8, (float) 5.2, (float) 4.5, (float) 4.75, (float) 4.7};

		SumToTextRoundExamples.trace(anObjects2);
		ParallelSumToTextRoundExamples.sectionSumAndToText(anObjects2);
		SumToTextRoundExamples.trace(anObjects2);


	}

}
