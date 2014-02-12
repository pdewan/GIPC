package examples.mvc.local.duplex;

import bus.uigen.ObjectEditor;

public class SampleRecord {
	public String string = "edit me";
	public int number = 0;
	public boolean flag = false;
	public enum Color {red, green, blue};
	public Color color = Color.red;	
	public void inc (int value) {
		number += value;
	}	
	public static void main (String[] args) {
//		ObjectEditor.edit(new SampleRecord());
		SampleRecord[] array = {new SampleRecord(), new SampleRecord()};
		ObjectEditor.edit(array);		
	}

}
