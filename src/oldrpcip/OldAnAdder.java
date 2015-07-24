package oldrpcip;

public class OldAnAdder implements OldAdder{

	@Override
	public Integer add(Integer p1, Integer p2) {
		return p1 + p2;
	}
	@Override
	public Object add(int p1, int p2) {
		return p1 + p2;
	}

	@Override
	public String add(String s1, String s2) {
		return s1 + s2;
	}

}
