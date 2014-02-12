package examples.mvc.local.simplex;
public class ASimplexFrostyModel implements SimplexFrostyModel {
	protected SimplexUpperCaser upperCaser;
	protected String input = "";
	public ASimplexFrostyModel(SimplexUpperCaser anUpperCaser) {
		upperCaser = anUpperCaser;
	}
		
	protected void printUpperCase(String anInput) {
		upperCaser.printUpperCase(anInput);
	}
	public void setInput(String anInput) {
		printUpperCase(anInput);
	}
	@Override
	public void setUpperCaser(SimplexUpperCaser anUpperCaser) {
		upperCaser = anUpperCaser;		
	}
}
