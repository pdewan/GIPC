package examples.openmp;

import java.util.Arrays;

public class SumToTextRound {
	protected static boolean trace = true;

	public static boolean isTrace() {
		return trace;
	}

	public static void setTrace(boolean newVal) {
		trace = newVal;
	}

	public static void trace(Object... anArgs) {

		if (isTrace())
			System.out.print(Thread.currentThread());
		for (Object anArg : anArgs) {
			System.out.print(" " + anArg);
		}
		System.out.println();
	}

	public static boolean processIteration(int anIndex, int aThreadNum, int aNumThreads) {

//		aThreadNum = OMP4J_THREAD_NUM;
//		aNumThreads = OMP4J_NUM_THREADS;
		return aNumThreads == 0 || (anIndex % aNumThreads == aThreadNum);
	}

//	public static float sum(float[] aList, int aThreadNum, int aNumThreads) {
//		float retVal = (float) 0.0;
//		trace("Sum Started");
//		for (int i = 0; i < aList.length; i++) {
//			if (processIteration(i, aThreadNum, aNumThreads) ) {
//			retVal += aList[i];
//			trace(retVal);
//			}
//		}
//		trace("Sum Ended");
//
//		return retVal;
//	}
	public static float parallelForSum(float[] aList) {
		float retVal = (float) 0.0;
		trace("Sum Started");
		// omp parallel for
		for (int i = 0; i < aList.length; i++) {
			retVal += aList[i];
		}
		trace("Sum Ended:" + retVal);
		return retVal;
	}

	public static float sum(float[] aList) {
		float retVal = (float) 0.0;
		trace("Sum Started");
		for (int i = 0; i < aList.length; i++) {
			retVal += aList[i];
		}
		trace("Sum Ended:" + retVal);
		return retVal;
	}

	// cannot call it toString
	public static String toText(float[] aList) {
		String retVal = "";
		trace("To Text Started");
		for (int i = 0; i < aList.length; i++) {
			retVal += " " + aList[i];
		}
		trace("To Text Ended:" + retVal);
		return retVal;
	}

	public static void sumAndToText(float[] aList) {
		// omp parallel threadNum(2)
		{
			int aThreadNum = 0;
			int aNumThreads = 0;
//			aThreadNum = OMP4J_THREAD_NUM;
//			aNumThreads = OMP4J_NUM_THREADS;			
			// omp barrier
			if (aThreadNum == 0) {
				float aSum = sum(aList);
				trace("Sum of rounded:" + aSum);
			} else {
				String aString = toText(aList);
				trace("ToText of rounded:" + aString);
			}
		}
	}

	public static void barrierRoundSumAndToText(float[] aList) {
		// omp parallel threadNum(2)
		{
			int aThreadNum = 0;
			int aNumThreads = 0;
//			aThreadNum = OMP4J_THREAD_NUM;
//			aNumThreads = OMP4J_NUM_THREADS;
			round(aList, aThreadNum, aNumThreads);
			trace("Before Barrier");
			// omp barrier
			if (aThreadNum == 0) {
				trace("After Barrier");
				float aSum = sum(aList);
//				trace("Sum of rounded:" + aSum);
			} else {
				trace("After Barrier");
				String aString = toText(aList);
//				trace("ToText of rounded:" + aString);
			}

		}
	}

	public static void sectionRoundSumAndToText(float[] aList) {
		// omp parallel threadNum(2)
		{
			int aThreadNum = 0;
			int aNumThreads = 0;
//			aThreadNum = OMP4J_THREAD_NUM;
//			aNumThreads = OMP4J_NUM_THREADS;
			round(aList, aThreadNum, aNumThreads);
			trace("Before Barrier");
			// omp barrier
			{
				// omp sections
				{
					// omp section
					sum(aList);// cannot assign it to a variable
					// omp section
					toText(aList);// cannot assign it to a variable
				}
			}
		}
	}

	public static void sectionSumAndToText(float[] aList) {
		// implicit parallel, explicit parallel does not work
		// omp sections
		{
			// omp section
			sum(aList);
			// omp section
			toText(aList);
		}

	}

	public static void threadUnawareRoundSumAndToText(float[] aList) {
		// omp parallel threadNum(2)
		{
			int aThreadNum = 0;
			int aNumThreads = 0;
//			aThreadNum = OMP4J_THREAD_NUM;
//			aNumThreads = OMP4J_NUM_THREADS;
			round(aList, aThreadNum, aNumThreads);
			// omp barrier
//			if (aThreadNum == 0) {
			float aSum = sum(aList);
			trace("Sum of rounded:" + aSum);
//			} else {
			String aString = toText(aList);
			trace("ToText of rounded:" + aString);
		}

//		}
	}

	public static void roundSumAndToTextNoBarrier(float[] aList) {
		// omp parallel threadNum(2)
		{
			int aThreadNum = 0;
			int aNumThreads = 0;
//			aThreadNum = OMP4J_THREAD_NUM;
//			aNumThreads = OMP4J_NUM_THREADS;
			round(aList, aThreadNum, aNumThreads);
			if (aThreadNum == 0) {
				float aSum = sum(aList);
				trace("Sum of rounded:" + aSum);
			} else {
				String aString = toText(aList);
				trace("ToText of rounded:" + aString);
			}

		}
	}

	public static void roundSumAndToTextBarrier(float[] aList) {
		// omp parallel threadNum(2)
		{
			int aThreadNum = 0;
			int aNumThreads = 0;
//			aThreadNum = OMP4J_THREAD_NUM;
//			aNumThreads = OMP4J_NUM_THREADS;
			round(aList, aThreadNum, aNumThreads);
			// omp barrier
			if (aThreadNum == 0) {
				float aSum = sum(aList);
				trace("Sum of rounded:" + aSum);
			} else {
				String aString = toText(aList);
				trace("ToText of rounded:" + aString);
			}

		}
	}

	public static void round(float[] aList, int aThreadNum, int aNumThreads) {
		trace("Round Started:" + aThreadNum + " " + Arrays.toString(aList));
		for (int i = 0; i < aList.length; i++) {
			if (processIteration(i, aThreadNum, aNumThreads)) {
				if (aThreadNum == 1) {
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				aList[i] = (float) Math.round(aList[i]);
				trace(aList[i]);
			}
		}
		trace("Round Ended:" + aThreadNum + " " + Arrays.toString(aList));

	}

	public static void parallelRound(float[] aList) {
		int i = 0;
		// omp parallel for threadNum(2)
		for (;;) {
			aList[i] = (float) Math.round(aList[i]);
			trace(aList[i]);
			i++;
			if (i == aList.length)
				break;
		}
	}

}
