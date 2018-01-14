package examples.nio.manager.mvc;

import java.nio.ByteBuffer;
import java.util.Scanner;

public class AMeaningOfLifeController implements MeaningOfLifeController{
	MeaningOfLifeModel model;
	Scanner scanner = new Scanner (System.in);
	public  AMeaningOfLifeController(MeaningOfLifeModel aModel) {
		model = aModel;
	}
	@Override
	public void processInput() {
		while (true) {
			System.out.println("Meaning of life?");
			String aMeaning = scanner.nextLine();
			model.setMeaning(aMeaning);			
		}	
		
	}

}
