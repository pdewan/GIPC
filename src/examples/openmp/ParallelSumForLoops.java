package examples.openmp;

public class ParallelSumForLoops {
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
		float retVal = (float) 0.0;
		trace("Sum Started");

		/* === OMP CONTEXT === */
		class OMPContext {
			public float[] param_aList;
			public float local_retVal;
		}
		final OMPContext ompContext_QDV = new OMPContext();
		ompContext_QDV.param_aList = aList;
		ompContext_QDV.local_retVal = retVal;
		final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int i_9Zs = 0; i_9Zs < ompContext_QDV.param_aList.length; i_9Zs++) {
			final int i = i_9Zs;
			ompExecutor.execute(new Runnable() {
				@Override
				public void run() {

					ompContext_QDV.local_retVal += ompContext_QDV.param_aList[i];
					trace("Intermediate Sum:" + ompContext_QDV.local_retVal);
				}
			});
		}
		ompExecutor.waitForExecution();

		trace("Sum Ended:" + retVal);
		return retVal;
	}

	public static float reducingSum(float[] aList) {
		float retVal = (float) 0.0;
		trace("Sum Started");
		int aNumThreads = 2;
		float[] aSums = { (float) 0.0, (float) 0.0 };

		/* === OMP CONTEXT === */
		class OMPContext_7Xl {
			public float[] param_aList;
			public float[] local_aSums;
		}
		final OMPContext_7Xl ompContext_OWA = new OMPContext_7Xl();
		ompContext_OWA.param_aList = aList;
		ompContext_OWA.local_aSums = aSums;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_P6x = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int i_d7b = 0; i_d7b < ompContext_OWA.param_aList.length; i_d7b++) {
			final int i = i_d7b;
			ompExecutor_P6x.execute(new Runnable() {
				@Override
				public void run() {

					int aThreadNum = 0;
					aThreadNum = ompExecutor_P6x.getThreadNum();
					ompContext_OWA.local_aSums[aThreadNum] += ompContext_OWA.param_aList[i];
					trace("Intermediate Sum:" + ompContext_OWA.local_aSums[aThreadNum]);
				}
			});
		}
		ompExecutor_P6x.waitForExecution();

		for (float aSum : aSums) {
			retVal += aSum;
		}
		trace("Sum Ended:" + retVal);
		return retVal;
	}

	public static float atomicSum(float[] aList) {
		float retVal = (float) 0.0;
		trace("Sum Started");

		/* === OMP CONTEXT === */
		class OMPContext_jTL {
			public float[] param_aList;
			public float local_retVal;
		}
		final OMPContext_jTL ompContext = new OMPContext_jTL();
		ompContext.param_aList = aList;
		ompContext.local_retVal = retVal;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_RLW = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int i_spE = 0; i_spE < ompContext.param_aList.length; i_spE++) {
			final int i = i_spE;
			ompExecutor_RLW.execute(new Runnable() {
				@Override
				public void run() {

					synchronized (ompContext) {
						{
							ompContext.local_retVal += ompContext.param_aList[i];
							trace("Intermediate Sum:" + ompContext.local_retVal);
						}
					}

				}
			});
		}
		ompExecutor_RLW.waitForExecution();

		trace("Sum Ended:" + retVal);
		return retVal;
	}

	public static float nonAtomicExpandedSum(float[] aList) {
		float retVal = (float) 0.0;
		trace("Sum Started");

		/* === OMP CONTEXT === */
		class OMPContext_WGO {
			public float[] param_aList;
			public float local_retVal;
		}
		final OMPContext_WGO ompContext_3SN = new OMPContext_WGO();
		ompContext_3SN.param_aList = aList;
		ompContext_3SN.local_retVal = retVal;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_Ybs = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int i_kZ1 = 0; i_kZ1 < ompContext_3SN.param_aList.length; i_kZ1++) {
			final int i = i_kZ1;
			ompExecutor_Ybs.execute(new Runnable() {
				@Override
				public void run() {

					float aRegister = ompContext_3SN.param_aList[i];
					aRegister++;
					try {
						Thread.sleep(1);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					ompContext_3SN.local_retVal = aRegister;
					trace("Intermediate Sum:" + ompContext_3SN.local_retVal);
				}
			});
		}
		ompExecutor_Ybs.waitForExecution();

		trace("Sum Ended:" + retVal);
		return retVal;
	}

	public static float atomicExpandedSum(float[] aList) {
		float retVal = (float) 0.0;
		trace("Sum Started");

		/* === OMP CONTEXT === */
		class OMPContext_Brf {
			public float[] param_aList;
			public float local_retVal;
		}
		final OMPContext_Brf ompContext_4IG = new OMPContext_Brf();
		ompContext_4IG.param_aList = aList;
		ompContext_4IG.local_retVal = retVal;
		final org.omp4j.runtime.IOMPExecutor ompExecutor_dhJ = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int i_Jcp = 0; i_Jcp < ompContext_4IG.param_aList.length; i_Jcp++) {
			final int i = i_Jcp;
			ompExecutor_dhJ.execute(new Runnable() {
				@Override
				public void run() {

					synchronized (ompContext_4IG) {
						{
							float aRegister = ompContext_4IG.param_aList[i];
							aRegister++;
							try {
								Thread.sleep(1);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}

							ompContext_4IG.local_retVal = aRegister;
							trace("Intermediate Sum:" + ompContext_4IG.local_retVal);
						}
					}

				}
			});
		}
		ompExecutor_dhJ.waitForExecution();

		trace("Sum Ended:" + retVal);
		return retVal;
	}
}
