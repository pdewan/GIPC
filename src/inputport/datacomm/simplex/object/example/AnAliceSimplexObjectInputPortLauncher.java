package inputport.datacomm.simplex.object.example;


public class AnAliceSimplexObjectInputPortLauncher {
	public static  String ALICE = "Alice";
	public static void main (String[] args) {		
		(new ASimplexObjectClientInputPortLauncher(ALICE)).launch();
	}
}
