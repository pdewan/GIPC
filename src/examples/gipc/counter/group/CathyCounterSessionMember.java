package examples.gipc.counter.group;

public class CathyCounterSessionMember extends ACounterSessionMember{
	static final int MY_PORT_NUMBER = 7003;
	static final String MY_NAME = "3";

	public static void main (String[] args) {
		init(MY_NAME, MY_PORT_NUMBER);
		doOperations();
	}
}
