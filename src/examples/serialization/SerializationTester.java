package examples.serialization;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Vector;

import serialization.Serializer;
import serialization.SerializerSelector;

public class SerializationTester {

	public static void main(String[] args) {

		testSerialization();
	}
	
	enum Color {RED,GREEN, BLUE}
	public static Serializer testSerialization() {
		Serializer serializer = SerializerSelector.createSerializer();
		return testSerialization(serializer);
	}

	public static Serializer testSerialization(Serializer serializer) {
		
		System.out.println ("I*** STARTING TEST SERIALIZATION WITH SERIALIZER:" + serializer);
		// part 1
		
		
		translate(serializer, 5);
		translate(serializer, (short)5);
		translate(serializer, (long)5);
		translate(serializer, 5.5);
		translate(serializer, (float) 5.5);
		translate (serializer, "hello world");
		translate(serializer, true);
		translate(serializer, Color.RED);
		Object[] values = { "Hello World", "Goodbye World", Color.GREEN };
		translate(serializer, values);
		List list = new ArrayList();
		list.add("Hello world");
		list.add(3);
		list.add(Color.BLUE);
		list.add(null);
		translate(serializer, list);
		list = new Vector();
		list.add("Hello world");
		list.add(5);
		list.add(Color.BLUE);
		list.add(null);
		translate(serializer, list);
		Map map = new HashMap();
		map.put("greeting", "ni hao");
		map.put(5, 4.0);
		translate(serializer, map);
		map = new Hashtable();
		map.put("greeting", "ni hao");
		map.put(5, 2.0);
		translate(serializer, map);
		Set<String> set = new HashSet();
		set.add("Hello world");
		set.add("Goodbye world");
		translate(serializer, set);
		list.add(set);
		map.put("greeting", "namaste");
		list.add(map);
		translate(serializer, list);
		// part 2
		List recursive = new ArrayList();
		recursive.add(null);
		recursive.add(values);
		recursive.add(recursive);
		recursive.add(list);
		translate(serializer, recursive);
		BMISpreadsheet bmi = new AnotherBMISpreadsheet();
		bmi.setHeight(2.0);
		bmi.setMale(true);
	    translate(serializer, bmi);
		NamedBMISpreadsheet namedBMI = new ANamedBMISpreadsheet();
		namedBMI.setName("Joe Doe");
		namedBMI.setHeight(2.0);
		translate(serializer, namedBMI);
		StringHistory stringHistory = new AStringHistory();
		stringHistory.add("James Dean");
		stringHistory.add("Joe Doe");
		stringHistory.add("Jane Smith");
		stringHistory.add("John Smith");
		translate(serializer, stringHistory);
		ObjectHistory objectHistory = new AnObjectHistory();
		objectHistory.add(objectHistory);
		objectHistory.add("hello");
		translate(serializer, objectHistory);
		List recursiveList = new ArrayList();
		recursiveList.add(recursiveList);
		translate(serializer, recursiveList);
		System.out.println ("I*** ENDED TEST SERIALIZATION WITH SERIALIZER:" + serializer);

		return serializer;

	}
	
	static String toString(Object a) {
		if (a == null) {
			return "null";
		}
		if (a.getClass().isArray()) {
			return Arrays.toString((Object[])a);
		}
		return a.toString();
	}

	public static Object translate(Serializer serializer, Object object) {
		try {
			System.out.println("Serializing " + toString(object));
			ByteBuffer buffer = serializer.outputBufferFromObject(object);
			Object readVal = serializer.objectFromInputBuffer(buffer);
			System.out.println("Deserialized " + toString(readVal));
			return readVal;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	//
	// static void testClassLoading() {
	// System.out.println( RMIClassLoader.getClassAnnotation
	// (ABMISpreadsheet.class));
	//
	// }

}
