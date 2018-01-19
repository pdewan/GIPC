package examples.nio.manager.mvc;

import java.nio.ByteBuffer;
import java.util.Scanner;
/**
 * Defines a controller object that allows the user to input a sequence of 
 * inputs. An input can be a (a) new model property value, or (b) a command
 * to make  a sequence of programmed changes to the model property. 
 * In case (a) it calls the model setter method once, and (b) it calls
 * the model setter multiple times and determines how long it takes to make the changes. 
 * 
 * @author Dewan
 *
 */
public class AMeaningOfLifeController implements MeaningOfLifeController{
	MeaningOfLifeModel model;
	public static final int NUM_BATCH_ITEMS = 10;
	public static final String EXPERiMENT_COMMAND_NAME = "experiment";
	public static final String PROGRAMMED_INPUT_1 = "42";
	public static final String PROGRAMMED_INPUT_2 = "29";

	Scanner scanner = new Scanner (System.in);
	public  AMeaningOfLifeController(MeaningOfLifeModel aModel) {
		model = aModel;
	}
	protected void runExperiment() {
		long aStartTime = System.currentTimeMillis();
		for (int i = 0; i < NUM_BATCH_ITEMS; i++) {
			model.setMeaning(PROGRAMMED_INPUT_1);
			model.setMeaning(PROGRAMMED_INPUT_2);
		}
		System.out.println("Time taken:" + (System.currentTimeMillis() - aStartTime));
	}
	@Override
	public void processInput() {
		while (true) {
			System.out.println("Meaning of life? (or enter \"" +  EXPERiMENT_COMMAND_NAME + "\" for  programmed input)");
			String anInput = scanner.nextLine();
			if (EXPERiMENT_COMMAND_NAME.startsWith(anInput.toLowerCase())) {
				runExperiment();
			} else {
				model.setMeaning(anInput);	
			}
		}	
		
	}

}
