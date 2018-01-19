package assignments.util.inputParameters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import util.interactiveMethodInvocation.ACommandToMethodCallTranslator;
import util.interactiveMethodInvocation.SimulationParametersController;
/**
 * Uses its super class to define and support syntax 
 * of interactive commands for invoking methods implemented in the class
 * SimulationParametersListener. 
 * 
 * Allows multiple objects implementing the observer class to be registered
 * and responds to an interactive command by calling the corresponding listener
 * method in each listener.
 *  
 * @author Dewan
 *
 */
public class ASimulationParametersController extends ACommandToMethodCallTranslator implements SimulationParametersController {
	protected List<SimulationParametersListener> simulationParameterListener = new ArrayList();
	
	@Override
	public void addSimulationParameterListener(SimulationParametersListener aListener){
		simulationParameterListener.add(aListener);
	}
	@Override
	protected void callMethod(Method aMethod, Object[] anArgs) {
		for (SimulationParametersListener aListener:simulationParameterListener) {
			try {
				aMethod.invoke(aListener, anArgs);
			} catch (IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	@Override
	public void processCommands() {
		processCommands(SimulationParametersListener.class);
		
	}

}
