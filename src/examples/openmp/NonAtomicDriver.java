package examples.openmp;

public class NonAtomicDriver {
	public static void main(String[] args) {
//		Random.setTrace(false);
//		ParallelRandom.setTrace(false);
		System.out.println("Single threaded random");
		Random.printRandom();
		System.out.println("Multi threaded random");
		ParallelRandom.printRandom();		
	}
}
