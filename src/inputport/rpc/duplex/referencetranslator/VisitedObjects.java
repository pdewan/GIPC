package inputport.rpc.duplex.referencetranslator;

public interface VisitedObjects {


	void visit(Object original, Object copy);
	
	Object getCopy(Object original);
	
}
