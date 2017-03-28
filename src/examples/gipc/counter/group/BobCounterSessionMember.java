package examples.gipc.counter.group;

public class BobCounterSessionMember extends ACounterSessionMember{
	static final int MY_PORT_NUMMBER = 7002;
	static final String MY_NAME = "2";

	public static void main (String[] args) {

		init(MY_NAME, MY_PORT_NUMMBER);
		doOperations();
	}
}
