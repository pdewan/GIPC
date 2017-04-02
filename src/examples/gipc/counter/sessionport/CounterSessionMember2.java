package examples.gipc.counter.sessionport;

public class CounterSessionMember2 extends ACounterSessionMember{
	static final int MY_PORT_NUMBER = 7002;
	static final String MY_NAME = "2";

	public static void main (String[] args) {
		beSessionMember(MY_NAME, MY_PORT_NUMBER);

	}
}
