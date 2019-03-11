package examples.openmp.samples;

public class PExample3 {
	public static void main(String[] args) {

		/* === OMP CONTEXT === */
		class OMPContext {
		}
		final OMPContext ompContext = new OMPContext();
		final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(
				Runtime.getRuntime().availableProcessors());
		/* === /OMP CONTEXT === */
		for (int ompI = 0; ompI < Runtime.getRuntime().availableProcessors(); ompI++) {
			final int ompJ = ompI;
			ompExecutor.execute(new Runnable() {
				@Override
				public void run() {
					{

						if (ompJ == 0) {
							{
								System.out.println("task1 start");
								System.out.println("task1 stop");
							}
						}

						else if (ompJ == 1) {
							{
								System.out.println("task2 start");
								System.out.println("task2 stop");
							}
						}

						else if (ompJ == 2) {
							{
								System.out.println("task3 start");
								System.out.println("task3 stop");
							}
						}

					}
				}
			});
		}
		ompExecutor.waitForExecution();

	}
}
