package examples.openmp;

import java.util.Arrays;

public class MiscExamples {
	public static String WHITES_SPACE = " ";
	public static void trace(Object... anArgs) {
		int threadNum = 0;
		int numThreads = 0;
//		threadNum = OMP4J_THREAD_NUM;
//		numTHreads = OMP4J_NUM_THREADS;
		if (numThreads > 0) {
			System.out.print(numThreads+":"+threadNum+":");
		}
		System.out.print(Thread.currentThread());
		for (Object anArg:anArgs) {
			System.out.print(":" + anArg);
		}
		System.out.println();
	}
	
	public static void round(Float[] aList) {
		// omp parallel for 
		for (int i = 0; i < aList.length; i++) {
 		aList[i] = (float) Math.round(aList[i]);
			trace(aList[i]);
		}
	}
	public static boolean processIteration (int anIndex, int aThreadNum, int aNumThreads) {
		
//		aThreadNum = OMP4J_THREAD_NUM;
//		aNumThreads = OMP4J_NUM_THREADS;
		return aNumThreads == 0 || (anIndex%aNumThreads ==  aThreadNum);
	}
	public static void round(Float[] aList, int aThreadNum, int aNumThreads) {
//		int aThreadNum = OMP4J_THREAD_NUM;
//		int aNumThreads = OMP4J_NUM_THREADS;
		trace("Round Started");
		for (int i = 0; i < aList.length; i++) {		
			if (processIteration(i, aThreadNum, aNumThreads) ) {
			aList[i] = (float) Math.round(aList[i]);
			trace(aList[i]);
			}
		}
	}
	
	public static Float sum(Float[] aList) {
		Float retVal = (float) 0.0;
		for (int i = 0; i < aList.length; i++) {
			retVal += aList[i];
			trace(retVal);
		}
		return retVal;
	}
	
	public static Float sum(Float[] aList, int aThreadNum, int aNumThreads) {
		Float retVal = (float) 0.0;
		trace("Sum Started");
		for (int i = 0; i < aList.length; i++) {
			if (processIteration(i, aThreadNum, aNumThreads) ) {
				
			retVal += aList[i];
			trace(retVal);
			}
		}
		return retVal;
	}
	
	public static void roundSum(Float[] aList) {
		//omp parallel threadNum(2)
		{
			int aThreadNum = 0;
			int aNumThreads = 0;
//			aThreadNum = OMP4J_THREAD_NUM;
//			aNumThreads = OMP4J_NUM_THREADS;
			round(aList, aThreadNum, aNumThreads);
			Float aSum = sum(aList, aThreadNum, aNumThreads);
			trace("Final Sum:" + aSum);			
		}
	}
	
	public static int atomicSum(short[] aList) {
		int retVal = 0;
		// omp parallel for 
		for (int i = 0; i < aList.length; i++) {
			//omp critical
			retVal += aList[i];
			trace(retVal);
		}
		return retVal;
	}
	public static int reducedSum(short[] aList) {
		int retVal = 0;
		// omp parallel for private(retVal)
		for (int i = 0; i < aList.length; i++) {
			retVal += aList[i];
			trace(retVal);
		}
		return retVal;
	}
	public static long product(short[] aList) {
		long retVal = 1;
		// omp parallel for
		for (int i = 0; i < aList.length; i++) {
			retVal *= aList[i];
			trace(retVal);
		}
		return retVal;
	}
	public static String concat(String[] aList) {
		String retVal = "";
		// omp parallel for
		for (int i = 0; i < aList.length; i++) {
			retVal += aList[i];
			trace(retVal);
		}
		return retVal;
	}
	public static short minus(int[] aList) {
		short retVal = 0;
		// omp parallel for
		for (int i = 0; i < aList.length; i++) {
			retVal -= aList[i];
			trace(retVal);
		}
		return retVal;
	}
	public static void print(String[][] aTable) {
		// omp parallel for
		for (int i=0; i<aTable.length;i++) {
			trace(i);
			print(aTable[i], Thread.currentThread());
		}
		
	}
	
	public static void print(String[] aList, Thread aParentThread) {
		// omp parallel for
		for (int i=0; i<aList.length;i++) {
			trace(i, aList[i]);			
		}		
	}
	public static void atomicPrint(String[] aList, Thread aParentThread) {
		// omp parallel for
		for (int i=0; i<aList.length;i++) {
			//omp critical
			trace(i, aList[i]);			
		}		
	}
	public static void atomicPrint(String[][] aTable) {
		// omp parallel for threadNum(2) 
		for (int i=0; i<aTable.length;i++) {
			trace(i);
			print(aTable[i], Thread.currentThread());
		}		
	}
	
}