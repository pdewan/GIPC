package serialization.dynamiccompilation;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.util.Arrays;

import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
public class MyDynamicCompilation {
  public static void main(String[] args) throws Exception {
    compileSourceFile(createSourceFile());
    greet();
  }
  
  public static File createSourceFile() throws Exception {
	  File sourceFile   = new File("/temp/Hello.java");
	    FileWriter writer = new FileWriter(sourceFile);
	    writer.write(
	            "public class Hello{ \n" +
	            " public void greet() { \n" +
	            "   System.out.println(\"Hello world\") ;\n" +
	            " }\n" +
	            "}"
	    );
	    writer.close();
	    return sourceFile;
  }

  
  public static void compileSourceFile(File sourceFile) throws Exception {
	  JavaCompiler compiler    = ToolProvider.getSystemJavaCompiler();
	    StandardJavaFileManager fileManager =
	        compiler.getStandardFileManager(null, null, null);
	    fileManager.setLocation(StandardLocation.CLASS_OUTPUT,
	                            Arrays.asList(new File("/temp")));
	    // Compile the file
	    CompilationTask  compilationTask = compiler.getTask(null, fileManager, null, null, null,
	               fileManager.getJavaFileObjectsFromFiles(Arrays.asList(sourceFile)));
	    compilationTask.call();
	    fileManager.close();
	    // delete the source file
	    // sourceFile.deleteOnExit();
  }
  public static void greet() {
    try {
      Class params[] = {};
      Object paramsObj[] = {};
      Class thisClass = Class.forName("Hello");
      Object iClass = thisClass.newInstance();
      Method thisMethod = thisClass.getDeclaredMethod("greet", params);
      thisMethod.invoke(iClass, paramsObj);
      }
    catch (Exception e) {
      e.printStackTrace();
    }
  }
}