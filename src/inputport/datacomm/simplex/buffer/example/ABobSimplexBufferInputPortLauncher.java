package inputport.datacomm.simplex.buffer.example;


public class ABobSimplexBufferInputPortLauncher {
	public static  String BOB = "Bob";
	public static void main (String[] args) {		
		(new ASimplexBufferClientInputPortLauncher(BOB)).launch();
	}
}
