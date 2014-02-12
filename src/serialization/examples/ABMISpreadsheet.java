package serialization.examples;
public class ABMISpreadsheet implements BMISpreadsheet {
	double height = 1.77;
	double weight = 75;	
	public double getWeight() {
		return weight;
	}
	public void setWeight(double newWeight) {
		weight = newWeight;
	}	
	public double getHeight() {
		return height;
	}
	public void setHeight(double newHeight) {
		height = newHeight;
	}	
	public double getBMI() {
		return weight/(height*height);
	}
	public String toString() {
		return super.toString() + "(" + getHeight() + "," + getWeight() + "," + getBMI()  + "," + isMale() + ")" ;
	}
	boolean male;

	public boolean isMale() {
		return male;
	}
	
	public void setMale(boolean newVal) {
		male = newVal;
	}
}
