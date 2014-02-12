package oldrpcip;

import java.util.HashMap;
import java.util.Map;

public class ReflectionUtility {
	
	static Map<String, Class> primitiveToClass = new HashMap();
	static Class forName(String typeName) {
		try {
		Class retVal = primitiveToClass.get(typeName);
		if (retVal == null)
			retVal =  Class.forName(typeName);
		return retVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	static {
		primitiveToClass.put("int", Integer.TYPE);
		primitiveToClass.put("boolean", Boolean.TYPE);
	}

}
