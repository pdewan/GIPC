package examples.openmp.samples;

public class Example2 {
	public static void main(String[] args) {

		// omp parallel for
		for (int i = 0; i < 50; i += 2) {
			System.out.print(" " + i);
		}
	}
}
