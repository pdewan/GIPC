package examples.openmp.samples;

public class Example5 {
	public static void main(String[] args) {
		// omp parallel
		{
			System.out.println("pre");

			// omp barrier
			{}

			System.out.println("post");
		}
	}
}