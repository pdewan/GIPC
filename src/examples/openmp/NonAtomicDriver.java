package examples.openmp;

public class NonAtomicDriver {
	public static void main(String[] args) {
//		AtomicExample.setTrace(false);
//		ParallelAtomicExample.setTrace(false);
		System.out.println("Single threaded random");
		RandomExample.printRandom();
		System.out.println("Multi threaded random");
		ParallelRandomExample.printRandom();		
	}
}
