package examples.openmp;

import java.util.HashMap;
import java.util.Map;

public class ForSumLoops {
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

	public static float nonAtomicSum(float[] aList) {
		float retVal = 0.0f;
		trace("Sum Started");
		// omp parallel for threadNum(2)
		for (int i = 0; i < aList.length; i++) {
			retVal += aList[i];
			trace("Intermediate Sum:" + retVal);
		}
		trace("Sum Ended:" + retVal);
		return retVal;
	}
	public static void add (Map<String, Integer> aMap, String aKey, Integer aValue) {
		Integer anOriginalValue = aMap.get(aKey);
		Integer aNewValue = aValue + (anOriginalValue == null?0:anOriginalValue);
		aMap.put(aKey, aValue); 
	}
	public static void mapPlus (Map<String, Integer> aMap, Map<String, Integer> anAddition) {
		for (String aKey:anAddition.keySet()) {
			add(aMap, aKey,anAddition.get(aKey));
		}
	}
	public static Map<String, Integer> wordCount(String[] aWords) {
		Map<String, Integer> aWordCount = new HashMap();
		float[] aSums = {0.0f, 0.0f};
		// omp parallel for threadNum(aNumThreads)
		for (int i = 0; i < aWords.length; i++) {
			add (aWordCount, aWords[i], 1);
		}		
		return aWordCount;
	}
	public static Map<String, Integer> reducableWordCount(String[] aWords) {
		Map<String, Integer> aWordCount = new HashMap();
		float[] aSums = {0.0f, 0.0f};
		// omp parallel for threadNum(aNumThreads)
		for (int i = 0; i < aWords.length; i++) {
			Map<String, Integer> anAddition = new HashMap();
			anAddition.put(aWords[i], 1);
			mapPlus(aWordCount, anAddition);
		}		
		return aWordCount;
	}
	
	public static float reducingSum(float[] aList) {
		float retVal = (float) 0.0;
		trace("Sum Started");
		int aNumThreads = 2;
		float[] aSums = {0.0f, 0.0f};
		// omp parallel for threadNum(aNumThreads) schedule(static, 128)
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

	public static float atomicSum(float[] aList) {
		float retVal = (float) 0.0;
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

	public static float nonAtomicExpandedSum(float[] aList) {
		float retVal = (float) 0.0;
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

	public static float atomicExpandedSum(float[] aList) {
		float retVal = (float) 0.0;
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
