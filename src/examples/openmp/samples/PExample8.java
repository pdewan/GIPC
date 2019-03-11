package examples.openmp.samples;

public class PExample8 {
	public static void main(String[] args) {

		/* === OMP CONTEXT === */
		class OMPContext {
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
						System.out.println("Thread #" + ompExecutor.getThreadNum() + "/" + ompExecutor.getNumThreads());
					}
				}
			});
		}
		ompExecutor.waitForExecution();

	}
}
