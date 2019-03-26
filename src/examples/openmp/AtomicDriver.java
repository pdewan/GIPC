package examples.openmp;

public class AtomicDriver {
	public static void main(String[] args) {
//		AtomicExample.setTrace(false);
//		ParallelAtomicExample.setTrace(false);
		System.out.println("Single threaded random");
		Random.atomicPrintRandom();
		System.out.println("Multi threaded random");
		ParallelRandom.atomicPrintRandom();		
	}
}
