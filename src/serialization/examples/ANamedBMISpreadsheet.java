package serialization.examples;

import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;

public class ANamedBMISpreadsheet extends AnotherBMISpreadsheet implements NamedBMISpreadsheet{
	public static final int HIGH_BMI = 27;
	transient boolean isOverWeight = calculateOverWeight();
	String name = "";
	boolean calculateOverWeight() {
		return bmi > HIGH_BMI;
	}	
	public boolean isOverWeight() {
		return isOverWeight;
	}
	public String getName() {
		return name;
	}	
	public void setName(String newValue) {
		name = newValue;		
	}	
	private void readObject(ObjectInputStream stream){
		try {
			stream.defaultReadObject(); 			
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
	@Override
	public String toString() {
		return super.toString() + "(" + getName() + "," + isOverWeight() + ")";
	}
	public void initSerializedObject() {
		super.initSerializedObject();
		isOverWeight = calculateOverWeight();
	}
	public void readExternal(ObjectInput in) {
		  try {
		    super.readExternal(in);
		    name = in.readLine();
		 } catch (Exception e) {e.printStackTrace();}
		}
		public void writeExternal(ObjectOutput out)  {
		  try {
		    super.writeExternal(out);
		    out.write(name.getBytes());
		  } catch (Exception e) {e.printStackTrace();}
		}


}
