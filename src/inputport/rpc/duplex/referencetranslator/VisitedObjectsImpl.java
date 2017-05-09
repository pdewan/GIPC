package inputport.rpc.duplex.referencetranslator;

import java.util.LinkedList;
import java.util.List;

public class VisitedObjectsImpl implements VisitedObjects {

	private List<Object> original_list;
	private List<Object> copy_list;
	
	public VisitedObjectsImpl() {
		this.original_list = new LinkedList<Object>();
		this.copy_list = new LinkedList<Object>();
	}
	
	@Override
	public void visit(Object original, Object copy) {
		original_list.add(original);
		copy_list.add(copy);
	}

	@Override
	public Object getCopy(Object original) {
		int idx = original_list.indexOf(original);
		if (idx < 0) return null;
		else return copy_list.get(idx);
	}

}
