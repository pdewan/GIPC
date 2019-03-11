package examples.openmp;

public class ParallelAtomicExample {
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

	public static void printRandom() {

		/* === OMP CONTEXT === */
		class OMPContext {
		}
		final OMPContext ompContext_iXm = new OMPContext();
		final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int ompI = 0; ompI < 2; ompI++) {
			ompExecutor.execute(new Runnable() {
				@Override
				public void run() {
					trace(Math.random());
				}
			});
		}
		ompExecutor.waitForExecution();

	}

	public static void atomicPrintRandom() {

		/* === OMP CONTEXT === */
		class OMPContext_gjW {
		}
		final OMPContext_gjW ompContext = new OMPContext_gjW();
		final org.omp4j.runtime.IOMPExecutor ompExecutor_QzD = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int ompI_rim = 0; ompI_rim < 2; ompI_rim++) {
			ompExecutor_QzD.execute(new Runnable() {
				@Override
				public void run() {
					{

						synchronized (ompContext) {
							trace(Math.random());
						}

					}
				}
			});
		}
		ompExecutor_QzD.waitForExecution();

	}
}