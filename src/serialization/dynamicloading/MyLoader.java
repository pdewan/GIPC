package serialization.dynamicloading;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
//java MyLoader serialization.dynamicloading.Tester
public class MyLoader {
   public static void main (String argv[]) throws Exception {

      URLClassLoader loader = new URLClassLoader(new URL[] { new URL("http://www.javacourses.com/classes/") });
    
      // Load class from class loader. argv[0] is the name of the class to be loaded
      Class c = loader.loadClass (argv[0]);

      // Create an instance of the class just loaded
      Object o = c.newInstance();

  }
   public static Class loadClass(String aURLOfClassesFolder,
			String aFullClassName) {
		try {
			URL[] urlSearchPath = { new URL(aURLOfClassesFolder) };
			URLClassLoader loader = new URLClassLoader(urlSearchPath);
			return loader.loadClass(aFullClassName);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
}
