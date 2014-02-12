package serialization.examples;

import java.io.ObjectInput;
import java.io.ObjectOutput;

import util.misc.Transient;


public class AnotherBMISpreadsheet implements BMISpreadsheet/* , Externalizable */{
	double height = 1.77;
	double weight = 75;
	transient double bmi = calculateBMI();
	public double getHeight() {
		return height;
	}
	public void setHeight(double newHeight) {
		height = newHeight;
		bmi = calculateBMI();
	}
	public double getWeight() {
		return weight;
	}
	public void setWeight(double newWeight) {
		weight = newWeight;
		bmi = calculateBMI();
	}
	@Transient
	public double getBMI() {
		return bmi;
	}
	double calculateBMI() {
		return weight / (height * height);
	}
	public String toString() {
		return super.toString() + "(" + getHeight() + "," + getWeight() + ","
				+ getBMI() + "," + isMale() +  ")";
	}

	public void initSerializedObject() {
		bmi = calculateBMI();
	}
	boolean male;

	public boolean isMale() {
		return male;
	}
	
	public void setMale(boolean newVal) {
		male = newVal;
	}

//	private synchronized void writeObject(java.io.ObjectOutputStream stream) {
//		try {
//			stream.defaultWriteObject();
//		} catch (Exception e) {
//
//		}
//	}
//	private void readObject(ObjectInputStream stream)
//			throws java.io.IOException {
//		try {
//			stream.defaultReadObject();
//			initSerializedObject();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public void readExternal(ObjectInput in) {
		try {
		  height = in.readDouble();
		  weight = in.readDouble();
		  initSerializedObject();
		} catch (Exception e) {e.printStackTrace();}
	}
	public void writeExternal(ObjectOutput out)  {
		try {
		  out.writeDouble(height);
		  out.writeDouble(weight);
		} catch (Exception e) {e.printStackTrace();}	
	}

}

