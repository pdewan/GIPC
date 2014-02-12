package port.old;


public class AnAdder implements Adder{

	@Override
	public Integer sum(Integer p1, Integer p2) {
		return p1 + p2;
	}
	@Override
	public int sum(int p1, int p2) {
		return p1 + p2;
	}

	@Override
	public String sum(String s1, String s2) {
		return s1 + s2;
	}
	@Override
	public Object objectSum(int p1, int p2) {
		return sum (p1, p2);
	}
	@Override
	public void printSum(int p1, int p2) {
		System.out.println("PrintSum:" + sum (p1,p2));
	}

}
