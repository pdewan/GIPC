package examples.mvc.local.simplex;

public class ASimplexUpperCaser  implements SimplexUpperCaser{
	public ASimplexUpperCaser() {
	}
	protected String toUpperCase(String anInput) {
		return anInput.toUpperCase();
	}
	
	public void printUpperCase(String anInput) {
		System.out.println(toUpperCase(anInput));
	}	
}
