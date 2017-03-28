package examples.gipc.counter.group;

public class AliceCounterSessionMember extends ACounterSessionMember{
	static final int MY_PORT_NUMBER = 7001;
	static final String MY_NAME = "1";

	public static void main (String[] args) {
		init(MY_NAME, MY_PORT_NUMBER);
		doOperations();
	}
}
