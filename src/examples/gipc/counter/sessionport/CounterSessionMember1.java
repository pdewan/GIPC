package examples.gipc.counter.sessionport;

public class CounterSessionMember1 extends ACounterSessionMember{
	static final int MY_PORT_NUMBER = 7001;
	static final String MY_NAME = "1";

	public static void main (String[] args) {
		beSessionMember(MY_NAME, MY_PORT_NUMBER);
//		doOperations();
	}
}
