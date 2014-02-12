package inputport.datacomm.simplex.object.mvc.example;

import java.io.Serializable;

public class ASimplexClientUpperCaseMarshaller implements SimplexUpperCaseClientMarshaller{

	@Override
	public Object marshallPrintUpperCase(String aString) {
		return new APrintUpperCaseCall(aString);
	}

}
