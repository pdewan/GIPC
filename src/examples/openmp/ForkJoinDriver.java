package examples.openmp;

public class ForkJoinDriver {
	public static void main(String[] args) {
//		AtomicExample.setTrace(false);
//		ParallelAtomicExample.setTrace(false);
		System.out.println("Single threaded random");
		Random.atomicPrintRandomForkJoin();
		System.out.println("Multi threaded random");
		ParallelRandom.atomicPrintRandomForkJoin();		
	}
}
