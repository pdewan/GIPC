package examples.openmp.samples;

public class PExample6 {
	public static void main(String[] args) {

		/* === OMP CONTEXT === */
		class OMPContext {
			public java.util.concurrent.atomic.AtomicBoolean singleLock = new java.util.concurrent.atomic.AtomicBoolean(
					false);
		}
		final OMPContext ompContext = new OMPContext();
		final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(
				Runtime.getRuntime().availableProcessors());
		/* === /OMP CONTEXT === */
		for (int ompI = 0; ompI < Runtime.getRuntime().availableProcessors(); ompI++) {
			ompExecutor.execute(new Runnable() {
				@Override
				public void run() {
					{
						System.out.println("By all 1");

						if (!ompContext.singleLock.getAndSet(true)) {
							{
							}

							System.out.println("only once");
						}

						System.out.println("By all 2");
					}
				}
			});
		}
		ompExecutor.waitForExecution();

	}
}