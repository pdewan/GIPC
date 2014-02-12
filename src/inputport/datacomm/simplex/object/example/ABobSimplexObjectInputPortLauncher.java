package inputport.datacomm.simplex.object.example;


public class ABobSimplexObjectInputPortLauncher {
	public static  String BOB = "Bob";
	public static void main (String[] args) {		
		(new ASimplexObjectClientInputPortLauncher(BOB)).launch();
	}
}
