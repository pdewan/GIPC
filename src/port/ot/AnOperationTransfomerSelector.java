package port.ot;

public class AnOperationTransfomerSelector {
	static OperationTransformerCreator factory = new AnOperationTranformerCreator();
	public static OperationTransformerCreator getOperationTransformerCreator() {
		return factory;
	}
	public static void setOperationTransformerCreator(OperationTransformerCreator newVal) {
		factory = newVal;
	}
	public static OperationTransformer getOperationTransformer() {
		return factory.getOperationTransformer();
	}

}
