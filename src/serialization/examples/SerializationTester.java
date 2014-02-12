package serialization.examples;

import java.nio.ByteBuffer;
import java.rmi.server.RMIClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import serialization.Serializer;
import serialization.SerializerSelector;

public class SerializationTester {

	public static void main(String[] args) {

		testSerialization();
	}

	public static void testSerialization() {
		// part 1
		Serializer serializer = SerializerSelector.createSerializer();
		String[] strings = { "Hello World", "Goodbye World" };
		List list = new ArrayList();
		list.add("Hello world");
		list.add(3);
		list.add(null);
		translate(serializer, list);
		Map map = new HashMap();
		map.put("greeting", "ni hao");
		map.put(5, 4.0);
		translate(serializer, map);
		Set<String> set = new HashSet();
		set.add("Hello world");
		set.add("Goodbye world");
		translate(serializer, set);
		list.add(set);
		list.add(map);
		translate(serializer, list);
		// part 2
		List recursive = new ArrayList();
		recursive.add(null);
		recursive.add(strings);
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

	}

	static void translate(Serializer serializer, Object object) {
		try {
			System.out.println("Serializing " + object);
			ByteBuffer buffer = serializer.outputBufferFromObject(object);
			Object readVal = serializer.objectFromInputBuffer(buffer);
			System.out.println("Deserialized " + readVal);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//
	// static void testClassLoading() {
	// System.out.println( RMIClassLoader.getClassAnnotation
	// (ABMISpreadsheet.class));
	//
	// }

}
