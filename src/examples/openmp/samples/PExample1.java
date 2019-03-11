package examples.openmp.samples;

public class PExample1 {
	public static void main(String[] args) {

		int foo = 5;

		/* === OMP CONTEXT === */
		class OMPContext {
			public int local_foo;
		}
		final OMPContext ompContext = new OMPContext();
		ompContext.local_foo = foo;
		final org.omp4j.runtime.IOMPExecutor ompExecutor = new org.omp4j.runtime.DynamicExecutor(
				Runtime.getRuntime().availableProcessors());
		/* === /OMP CONTEXT === */
		for (int ompI = 0; ompI < Runtime.getRuntime().availableProcessors(); ompI++) {
			ompExecutor.execute(new Runnable() {
				@Override
				public void run() {
					{
						System.out.println("hello");
						System.out.println("world");
						System.out.println(ompContext.local_foo);
//						System.out.println(foo);

					}
				}
			});
		}
		ompExecutor.waitForExecution();
		foo = ompContext.local_foo;

		System.out.println("last line");
	}
}
