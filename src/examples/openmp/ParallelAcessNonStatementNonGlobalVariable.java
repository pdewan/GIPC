package examples.openmp;

public class ParallelAcessNonStatementNonGlobalVariable {
	public static void accessNonStatementNonGlobal() {
		int nonStatementNonGlobal = 0;
		/* === OMP CONTEXT === */
		class OMPContext {
			public int local_nonStatementNonGlobal;
		}
		final OMPContext ompContext = new OMPContext();
		ompContext.local_nonStatementNonGlobal = nonStatementNonGlobal;
		final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int ompI = 0; ompI < 2; ompI++) {
			ompExecutor.execute(new Runnable() {
				@Override
				public void run() {
					ompContext.local_nonStatementNonGlobal++;
				}
			});
		}
		ompExecutor.waitForExecution();
		nonStatementNonGlobal = ompContext.local_nonStatementNonGlobal;

	}
}