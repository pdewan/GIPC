package port.ot;



public class AnOperationTransformer implements OperationTransformer {
	public Edit transform (TransformableEdit transformed, TransformableEdit other) {
		//Message.info("original:" +  transformed + ", other: " + other);
		int transformedIndex = transformed.getEdit().getIndex();
		char ch;
		if (transformedIndex > other.getEdit().getIndex())
			transformedIndex++;
		else if (transformedIndex == other.getEdit().getIndex()) {
//			if (transformed.getEdit().getChar() == other.getEdit().getChar())
//				return null;
			if (!transformed.isServer())
				transformedIndex++;
		}
		CharInsertion retVal = new ACharInsertion(transformed.getEdit().getName(), 
				transformedIndex, transformed.getEdit().getChar());
		//Message.info("transformed:" +  retVal + ", other: " + other);
		return retVal;
	}
}
