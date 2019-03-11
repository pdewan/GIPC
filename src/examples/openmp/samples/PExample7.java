package examples.openmp.samples;

public class PExample7 {
	/**
	 * Let's assume that we have e.g. 8 core computer and need to run parallel
	 * section only twice
	 */
	public static void main(String[] args) {

		/* === OMP CONTEXT === */
		class OMPContext {
		}
		final OMPContext ompContext = new OMPContext();
		final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(2);
		/* === /OMP CONTEXT === */
		for (int ompI = 0; ompI < 2; ompI++) {
			ompExecutor.execute(new Runnable() {
				@Override
				public void run() {
					{
						System.out.println("This is printed only twice.");
					}
				}
			});
		}
		ompExecutor.waitForExecution();

	}
}
