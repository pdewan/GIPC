package examples.nio.manager.mvc;

import java.nio.ByteBuffer;
import java.util.Scanner;

public class AMeaningOfLifeController implements MeaningOfLifeController{
	MeaningOfLifeModel model;
	public static final int NUM_BATCH_ITEMS = 10;
	public static final String BATCH_COMMAND_NAME = "batch";
	public static final String PROGRAMMED_INPUT_1 = "42";
	public static final String PROGRAMMED_INPUT_2 = "29";

	Scanner scanner = new Scanner (System.in);
	public  AMeaningOfLifeController(MeaningOfLifeModel aModel) {
		model = aModel;
	}
	protected void provideBatchInput() {
		for (int i = 0; i < NUM_BATCH_ITEMS; i++) {
			model.setMeaning(PROGRAMMED_INPUT_1);
			model.setMeaning(PROGRAMMED_INPUT_2);

		}
	}
	@Override
	public void processInput() {
		while (true) {
			System.out.println("Meaning of life? (or batch for  programmed input)");
			String anInput = scanner.nextLine();
			if (BATCH_COMMAND_NAME.startsWith(anInput.toLowerCase())) {
				provideBatchInput();
			} else {
				model.setMeaning(anInput);	
			}
		}	
		
	}

}
