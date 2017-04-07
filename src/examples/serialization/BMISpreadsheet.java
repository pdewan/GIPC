package examples.serialization;

import java.io.Serializable;

public interface BMISpreadsheet extends Serializable {
	double getWeight();
	void setWeight(double newWeight);	
	double getHeight();
	void setHeight(double newHeight);	
	double getBMI();
	boolean isMale();
	void setMale(boolean newVal);
	
}
