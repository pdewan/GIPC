package inputport.datacomm.simplex.object.mvc.example;

public class APrintUpperCaseCall implements PrintUpperCaseCall{
	String string;
	public APrintUpperCaseCall(String aString) {
		string = aString;
	}

	@Override
	public String getString() {
		return string;
	}

}
