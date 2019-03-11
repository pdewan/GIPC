package examples.openmp.samples;

public class PExample2 {
	public static void main(String[] args) {

		/* === OMP CONTEXT === */
		class OMPContext {
		}
		final OMPContext ompContext = new OMPContext();
		final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(
				Runtime.getRuntime().availableProcessors());
		/* === /OMP CONTEXT === */
		for (int i_Ray = 0; i_Ray < 50; i_Ray += 2) {
			final int i = i_Ray;
			ompExecutor.execute(new Runnable() {
				@Override
				public void run() {

					System.out.print(" " + i);
				}
			});
		}
		ompExecutor.waitForExecution();

	}
}
