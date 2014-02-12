package inputport.datacomm.simplex.buffer.example;


public class AnAliceSimplexBufferInputPortLauncher {
	public static  String ALICE = "Alice";
	public static void main (String[] args) {		
		(new ASimplexBufferClientInputPortLauncher(ALICE)).launch();
	}
}
