package examples.openmp;

public class BarrierExample {
	public static void trace(Object... anArgs) {
		int threadNum = 0;
		int numThreads = 0;
		if (numThreads > 0) {
			System.out.print(numThreads + ":" + threadNum + ":");
		}
		System.out.print(Thread.currentThread());
		for (Object anArg : anArgs) {
			System.out.print(":" + anArg);
		}
		System.out.println();
	}

	public static boolean processIteration(int anIndex, int aThreadNum, int aNumThreads) {

//		aThreadNum = OMP4J_THREAD_NUM;
//		aNumThreads = OMP4J_NUM_THREADS;
		return aNumThreads == 0 || (anIndex % aNumThreads == aThreadNum);
	}

//	public static Float sum(Float[] aList, int aThreadNum, int aNumThreads) {
//		Float retVal = (float) 0.0;
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
	public static Float sum(Float[] aList) {
		Float retVal = (float) 0.0;
		trace("Sum Started");
		for (int i = 0; i < aList.length; i++) {
			retVal += aList[i];
		}
		trace("Sum Ended");
		return retVal;
	}

	public static String toText(Float[] aList) {
		String retVal = "";
		trace("To String Started");
		for (int i = 0; i < aList.length; i++) {
			retVal += aList[i];
		}
		trace("To String Ended");
		return retVal;
	}

	public static void roundSumAndToText(Float[] aList) {
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
				Float aSum = sum(aList);
				trace("Sum of rounded:" + aSum);
			} else {
				trace("After Barrier");
				String aString = toText(aList);
				trace("ToText of rounded:" + aString);
			}

		}
	}

	public static void round(Float[] aList, int aThreadNum, int aNumThreads) {

		trace("Round Started:" + aThreadNum);
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
		trace("Round Ended:" + aThreadNum);

	}

}
