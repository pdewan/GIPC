package example.assignments.util.inputParameters;

import assignments.util.inputParameters.ASimulationParametersController;
import util.interactiveMethodInvocation.SimulationParametersController;
/**
 * Shows how one can gather dynamic simulation parameters from
 * the user and call listener methods in response, which can react to changes
 * to these parameters. 
 * 
 * @author Dewan
 *
 */
public class ExampleSimulationParametersControllerUser  {

	
/**
 * Run this program see how the following inputs are translated to method calls:
 * p
 * a true
 * a false
 * i true
 * atomic true
 * e
 * l false
 * q 0
 */
	public static void main(String[] args) {
		/*
		 * Instantiate the class that will process user commands.
		 */
		SimulationParametersController aSimulationParametersController = 
				new ASimulationParametersController();
		/*
		 * Register with this instance our listener object for processing the user
		 * commands
		 */
		aSimulationParametersController.addSimulationParameterListener(new AnExampleSimulationParametersListener());
		aSimulationParametersController.processCommands(); // start the console loop
		
	}

	
}
