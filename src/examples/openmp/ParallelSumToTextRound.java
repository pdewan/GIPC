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
	public static float parallelForSum(float[] aList) {
		float retVal = (float) 0.0;
		trace("Sum Started");

		/* === OMP CONTEXT === */
		class OMPContext {
			public float[] param_aList;
			public float local_retVal;
		}
		final OMPContext ompContext = new OMPContext();
		ompContext.param_aList = aList;
		ompContext.local_retVal = retVal;
		final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(
				Runtime.getRuntime().availableProcessors());
		/* === /OMP CONTEXT === */
		for (int i_VSm = 0; i_VSm < ompContext.param_aList.length; i_VSm++) {
			final int i = i_VSm;
			ompExecutor.execute(new Runnable() {
				@Override
				public void run() {

					ompContext.local_retVal += ompContext.param_aList[i];
				}
			});
		}
		ompExecutor.waitForExecution();

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

		/* === OMP CONTEXT === */
		class OMPContext_qIQ {
			public float[] param_aList;
		}
		final OMPContext_qIQ ompContext_1BB = new OMPContext_qIQ();
		ompContext_1BB.param_aList = aList;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_dxr = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int ompI = 0; ompI < 2; ompI++) {
			ompExecutor_dxr.execute(new Runnable() {
				@Override
				public void run() {
					{
						int aThreadNum = 0;
						int aNumThreads = 0;
						aThreadNum = ompExecutor_dxr.getThreadNum();
						aNumThreads = ompExecutor_dxr.getNumThreads();

						ompExecutor_dxr.hitBarrier("barrier");
						if (aThreadNum == 0) {
							float aSum = sum(ompContext_1BB.param_aList);
							trace("Sum of rounded:" + aSum);
						} else {
							String aString = toText(ompContext_1BB.param_aList);
							trace("ToText of rounded:" + aString);
						}
					}
				}
			});
		}
		ompExecutor_dxr.waitForExecution();

	}

	public static void barrierRoundSumAndToText(float[] aList) {

		/* === OMP CONTEXT === */
		class OMPContext_GVt {
			public float[] param_aList;
		}
		final OMPContext_GVt ompContext_ATj = new OMPContext_GVt();
		ompContext_ATj.param_aList = aList;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_H7C = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int ompI_RP2 = 0; ompI_RP2 < 2; ompI_RP2++) {
			ompExecutor_H7C.execute(new Runnable() {
				@Override
				public void run() {
					{
						int aThreadNum = 0;
						int aNumThreads = 0;
						aThreadNum = ompExecutor_H7C.getThreadNum();
						aNumThreads = ompExecutor_H7C.getNumThreads();
						round(ompContext_ATj.param_aList, aThreadNum, aNumThreads);
						trace("Before Barrier");

						ompExecutor_H7C.hitBarrier("barrier_HRx");
						if (aThreadNum == 0) {
							trace("After Barrier");
							float aSum = sum(ompContext_ATj.param_aList);
							trace("Sum of rounded:" + aSum);
						} else {
							trace("After Barrier");
							String aString = toText(ompContext_ATj.param_aList);
							trace("ToText of rounded:" + aString);
						}

					}
				}
			});
		}
		ompExecutor_H7C.waitForExecution();

	}

	public static void sectionRoundSumAndToText(float[] aList) {

		/* === OMP CONTEXT === */
		class OMPContext_1Pc {
			public float[] param_aList;
		}
		final OMPContext_1Pc ompContext_Ygy = new OMPContext_1Pc();
		ompContext_Ygy.param_aList = aList;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_mpw = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int ompI_R1s = 0; ompI_R1s < 2; ompI_R1s++) {
			ompExecutor_mpw.execute(new Runnable() {
				@Override
				public void run() {
					{
						int aThreadNum = 0;
						int aNumThreads = 0;
						aThreadNum = ompExecutor_mpw.getThreadNum();
						aNumThreads = ompExecutor_mpw.getNumThreads();
						round(ompContext_Ygy.param_aList, aThreadNum, aNumThreads);
						trace("Before Barrier");

						ompExecutor_mpw.hitBarrier("barrier_iK4");
						{

							/* === OMP CONTEXT === */
							class OMPContext_wkk {
							}
							final OMPContext_wkk ompContext_iVk = new OMPContext_wkk();
							final org.omp4j.runtime.IOMPExecutor ompExecutor_lRg = new org.omp4j.runtime.DynamicExecutor(
									Runtime.getRuntime().availableProcessors());
							/* === /OMP CONTEXT === */
							for (int ompI_mqT = 0; ompI_mqT < Runtime.getRuntime().availableProcessors(); ompI_mqT++) {
								final int ompJ_eiY = ompI_mqT;
								ompExecutor_lRg.execute(new Runnable() {
									@Override
									public void run() {
										{

											if (ompJ_eiY == 0) {
												sum(ompContext_Ygy.param_aList);
											}
//cannot assign it to a variable

											else if (ompJ_eiY == 1) {
												toText(ompContext_Ygy.param_aList);
											}
//cannot assign it to a variable
										}
									}
								});
							}
							ompExecutor_lRg.waitForExecution();

						}
					}
				}
			});
		}
		ompExecutor_mpw.waitForExecution();

	}

	public static void sectionSumAndToText(float[] aList) {
		// implicit parallel, explicit parallel does not work

		/* === OMP CONTEXT === */
		class OMPContext_Mrp {
			public float[] param_aList;
		}
		final OMPContext_Mrp ompContext_72U = new OMPContext_Mrp();
		ompContext_72U.param_aList = aList;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_6II = new org.omp4j.runtime.DynamicExecutor(
				Runtime.getRuntime().availableProcessors());
		/* === /OMP CONTEXT === */
		for (int ompI_xph = 0; ompI_xph < Runtime.getRuntime().availableProcessors(); ompI_xph++) {
			final int ompJ = ompI_xph;
			ompExecutor_6II.execute(new Runnable() {
				@Override
				public void run() {
					{

						if (ompJ == 0) {
							sum(ompContext_72U.param_aList);
						}

						else if (ompJ == 1) {
							toText(ompContext_72U.param_aList);
						}

					}
				}
			});
		}
		ompExecutor_6II.waitForExecution();

	}

	public static void threadUnawareRoundSumAndToText(float[] aList) {

		/* === OMP CONTEXT === */
		class OMPContext_IYx {
			public float[] param_aList;
		}
		final OMPContext_IYx ompContext_9sW = new OMPContext_IYx();
		ompContext_9sW.param_aList = aList;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_gYA = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int ompI_QtE = 0; ompI_QtE < 2; ompI_QtE++) {
			ompExecutor_gYA.execute(new Runnable() {
				@Override
				public void run() {
					{
						int aThreadNum = 0;
						int aNumThreads = 0;
						aThreadNum = ompExecutor_gYA.getThreadNum();
						aNumThreads = ompExecutor_gYA.getNumThreads();
						round(ompContext_9sW.param_aList, aThreadNum, aNumThreads);
						// omp barrier
//			if (aThreadNum == 0) {
						float aSum = sum(ompContext_9sW.param_aList);
						trace("Sum of rounded:" + aSum);
//			} else {
						String aString = toText(ompContext_9sW.param_aList);
						trace("ToText of rounded:" + aString);
					}
				}
			});
		}
		ompExecutor_gYA.waitForExecution();

//		}
	}

	public static void roundSumAndToTextNoBarrier(float[] aList) {

		/* === OMP CONTEXT === */
		class OMPContext_RjH {
			public float[] param_aList;
		}
		final OMPContext_RjH ompContext_rck = new OMPContext_RjH();
		ompContext_rck.param_aList = aList;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_ic9 = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int ompI_3kW = 0; ompI_3kW < 2; ompI_3kW++) {
			ompExecutor_ic9.execute(new Runnable() {
				@Override
				public void run() {
					{
						int aThreadNum = 0;
						int aNumThreads = 0;
						aThreadNum = ompExecutor_ic9.getThreadNum();
						aNumThreads = ompExecutor_ic9.getNumThreads();
						round(ompContext_rck.param_aList, aThreadNum, aNumThreads);
						if (aThreadNum == 0) {
							float aSum = sum(ompContext_rck.param_aList);
							trace("Sum of rounded:" + aSum);
						} else {
							String aString = toText(ompContext_rck.param_aList);
							trace("ToText of rounded:" + aString);
						}

					}
				}
			});
		}
		ompExecutor_ic9.waitForExecution();

	}

	public static void roundSumAndToTextBarrier(float[] aList) {

		/* === OMP CONTEXT === */
		class OMPContext_mbF {
			public float[] param_aList;
		}
		final OMPContext_mbF ompContext_ewM = new OMPContext_mbF();
		ompContext_ewM.param_aList = aList;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_XSv = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int ompI_Ipc = 0; ompI_Ipc < 2; ompI_Ipc++) {
			ompExecutor_XSv.execute(new Runnable() {
				@Override
				public void run() {
					{
						int aThreadNum = 0;
						int aNumThreads = 0;
						aThreadNum = ompExecutor_XSv.getThreadNum();
						aNumThreads = ompExecutor_XSv.getNumThreads();
						round(ompContext_ewM.param_aList, aThreadNum, aNumThreads);
						trace("Before Barrier");
						ompExecutor_XSv.hitBarrier("barrier_p1R");
						if (aThreadNum == 0) {
							float aSum = sum(ompContext_ewM.param_aList);
							trace("Sum of rounded:" + aSum);
						} else {
							String aString = toText(ompContext_ewM.param_aList);
							trace("ToText of rounded:" + aString);
						}

					}
				}
			});
		}
		ompExecutor_XSv.waitForExecution();

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
}