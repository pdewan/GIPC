package examples.openmp;

public class ForLoops {
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

	public static Float nonAtomicSum(Float[] aList) {
		Float retVal = (float) 0.0;
		trace("Sum Started");
		// omp parallel for threadNum(2)
		for (int i = 0; i < aList.length; i++) {
			retVal += aList[i];
			trace("Intermediate Sum:" + retVal);
		}
		trace("Sum Ended:" + retVal);
		return retVal;
	}
	public static Float reducingSum(Float[] aList) {
		Float retVal = (float) 0.0;
		trace("Sum Started");
		int aNumThreads = 2;
		Float[] aSums = {(float) 0.0, (float) 0.0};
		// omp parallel for threadNum(aNumThreads)
		for (int i = 0; i < aList.length; i++) {
			int aThreadNum = 0;
//			aThreadNum = OMP4J_THREAD_NUM;
			aSums[aThreadNum] += aList[i];
			trace("Intermediate Sum:" + aSums[aThreadNum]);
		}
		for (float aSum:aSums) {
			retVal += aSum;			
		}
		trace("Sum Ended:" + retVal);
		return retVal;
	}

	public static Float atomicSum(Float[] aList) {
		Float retVal = (float) 0.0;
		trace("Sum Started");
		// omp parallel for threadNum(2)
		for (int i = 0; i < aList.length; i++) {
			// omp critical
			{
				retVal += aList[i];
				trace("Intermediate Sum:" + retVal);
			}
		}
		trace("Sum Ended:" + retVal);
		return retVal;
	}

	public static Float nonAtomicExpandedSum(Float[] aList) {
		Float retVal = (float) 0.0;
		trace("Sum Started");
		// omp parallel for threadNum(2)
		for (int i = 0; i < aList.length; i++) {
			float aRegister = aList[i];
			aRegister++;
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			retVal = aRegister;
			trace("Intermediate Sum:" + retVal);
		}
		trace("Sum Ended:" + retVal);
		return retVal;
	}

	public static Float atomicExpandedSum(Float[] aList) {
		Float retVal = (float) 0.0;
		trace("Sum Started");
		// omp parallel for threadNum(2)
		for (int i = 0; i < aList.length; i++) {
			// omp critical
			{
				float aRegister = aList[i];
				aRegister++;
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				retVal = aRegister;
				trace("Intermediate Sum:" + retVal);
			}
		}
		trace("Sum Ended:" + retVal);
		return retVal;
	}

}
