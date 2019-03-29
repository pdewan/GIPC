package examples.openmp;

import java.util.Arrays;

public class ParallelSumToTextRound {
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
			retVal += aList[i];
		}
		trace("To String Ended:" + retVal);
		return retVal;
	}

	public static void barrierRoundSumAndToText(float[] aList) {
		

		/* === OMP CONTEXT === */
class OMPContext {
	public float[] param_aList;
}
final OMPContext ompContext = new OMPContext();
ompContext.param_aList = aList;
final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(2);
/* === /OMP CONTEXT === */
for (int ompI = 0; ompI < 2; ompI++) {
	ompExecutor.execute(new Runnable(){
		@Override
		public void run() {
{
			int aThreadNum = 0;
			int aNumThreads = 0;
//			aThreadNum = OMP4J_THREAD_NUM;
//			aNumThreads = OMP4J_NUM_THREADS;
			round(ompContext.param_aList, aThreadNum, aNumThreads);
			trace("Before Barrier");
			

			ompExecutor.hitBarrier("barrier");
if (aThreadNum == 0) {
				trace("After Barrier");
				float aSum = sum(ompContext.param_aList);
//				trace("Sum of rounded:" + aSum);
			} else {
				trace("After Barrier");
				String aString = toText(ompContext.param_aList);
//				trace("ToText of rounded:" + aString);
			}

		}		}
	});
}
ompExecutor.waitForExecution();

	}

	public static void sectionRoundSumAndToText(float[] aList) {
		

		/* === OMP CONTEXT === */
class OMPContext_wmG {
	public float[] param_aList;
}
final OMPContext_wmG ompContext_KEi = new OMPContext_wmG();
ompContext_KEi.param_aList = aList;
final org.omp4j.runtime.IOMPExecutor ompExecutor_mZK = new org.omp4j.runtime.DynamicExecutor(2);
/* === /OMP CONTEXT === */
for (int ompI_Poe = 0; ompI_Poe < 2; ompI_Poe++) {
	ompExecutor_mZK.execute(new Runnable(){
		@Override
		public void run() {
{
			int aThreadNum = 0;
			int aNumThreads = 0;
//			aThreadNum = OMP4J_THREAD_NUM;
//			aNumThreads = OMP4J_NUM_THREADS;
			round(ompContext_KEi.param_aList, aThreadNum, aNumThreads);
			trace("Before Barrier");
			

			ompExecutor_mZK.hitBarrier("barrier_wdh");
{
				

				/* === OMP CONTEXT === */
class OMPContext_smv {
}
final OMPContext_smv ompContext_BZN = new OMPContext_smv();
final org.omp4j.runtime.IOMPExecutor ompExecutor_xRI = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int ompI_clq = 0; ompI_clq < Runtime.getRuntime().availableProcessors(); ompI_clq++) {
	final int ompJ_A4g = ompI_clq;
	ompExecutor_xRI.execute(new Runnable(){
		@Override
		public void run() {
{
				

				if (ompJ_A4g == 0) {
sum(ompContext_KEi.param_aList);}
//cannot assign it to a variable
				

				else if (ompJ_A4g == 1) {
toText(ompContext_KEi.param_aList);}
//cannot assign it to a variable
				}		}
	});
}
ompExecutor_xRI.waitForExecution();


			}
		}		}
	});
}
ompExecutor_mZK.waitForExecution();

	}

	public static void sectionSumAndToText(float[] aList) {

		

		/* === OMP CONTEXT === */
class OMPContext_9sG {
	public float[] param_aList;
}
final OMPContext_9sG ompContext_5Xr = new OMPContext_9sG();
ompContext_5Xr.param_aList = aList;
final org.omp4j.runtime.IOMPExecutor ompExecutor_AUa = new org.omp4j.runtime.DynamicExecutor(Runtime.getRuntime().availableProcessors());
/* === /OMP CONTEXT === */
for (int ompI_lg2 = 0; ompI_lg2 < Runtime.getRuntime().availableProcessors(); ompI_lg2++) {
	final int ompJ = ompI_lg2;
	ompExecutor_AUa.execute(new Runnable(){
		@Override
		public void run() {
{
			

			if (ompJ == 0) {
sum(ompContext_5Xr.param_aList);}

			

			else if (ompJ == 1) {
toText(ompContext_5Xr.param_aList);}

		}		}
	});
}
ompExecutor_AUa.waitForExecution();


	}

	public static void roundSumAndToText(float[] aList) {

		/* === OMP CONTEXT === */
		class OMPContext_aoY {
			public float[] param_aList;
		}
		final OMPContext_aoY ompContext_oyo = new OMPContext_aoY();
		ompContext_oyo.param_aList = aList;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_Ssw = new org.omp4j.runtime.DynamicExecutor(
				2);
		/* === /OMP CONTEXT === */
		for (int ompI_NxT = 0; ompI_NxT < 2; ompI_NxT++) {
			ompExecutor_Ssw.execute(new Runnable() {
				@Override
				public void run() {
					{
						int aThreadNum = 0;
						int aNumThreads = 0;
						// aThreadNum = OMP4J_THREAD_NUM;
						// aNumThreads = OMP4J_NUM_THREADS;
						round(ompContext_oyo.param_aList, aThreadNum,
								aNumThreads);

						ompExecutor_Ssw.hitBarrier("barrier_e1w");
						if (aThreadNum == 0) {
							float aSum = sum(ompContext_oyo.param_aList);
							trace("Sum of rounded:" + aSum);
						} else {
							String aString = toText(ompContext_oyo.param_aList);
							trace("ToText of rounded:" + aString);
						}

					}
				}
			});
		}
		ompExecutor_Ssw.waitForExecution();

	}
	public static void roundSumAndToTextNoBarrier(float[] aList) {

		/* === OMP CONTEXT === */
		class OMPContext_aoY {
			public float[] param_aList;
		}
		final OMPContext_aoY ompContext_oyo = new OMPContext_aoY();
		ompContext_oyo.param_aList = aList;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_Ssw = new org.omp4j.runtime.DynamicExecutor(
				2);
		/* === /OMP CONTEXT === */
		for (int ompI_NxT = 0; ompI_NxT < 2; ompI_NxT++) {
			ompExecutor_Ssw.execute(new Runnable() {
				@Override
				public void run() {
					{
						int aThreadNum = 0;
						int aNumThreads = 0;
						// aThreadNum = OMP4J_THREAD_NUM;
						// aNumThreads = OMP4J_NUM_THREADS;
						round(ompContext_oyo.param_aList, aThreadNum,
								aNumThreads);

//						ompExecutor_Ssw.hitBarrier("barrier_e1w");
						if (aThreadNum == 0) {
							float aSum = sum(ompContext_oyo.param_aList);
							trace("Sum of rounded:" + aSum);
						} else {
							String aString = toText(ompContext_oyo.param_aList);
							trace("ToText of rounded:" + aString);
						}

					}
				}
			});
		}
		ompExecutor_Ssw.waitForExecution();

	}

	public static void round(float[] aList, int aThreadNum, int aNumThreads) {

		trace("Round Started:" + Arrays.toString(aList));
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
		trace("Round Ended:" + Arrays.toString(aList));

	}

}
