package inputport.rpc.duplex.referencetranslator;

public interface ReferenceTranslator {

	Object translate(Object o, VisitedObjects visited);
	
}
