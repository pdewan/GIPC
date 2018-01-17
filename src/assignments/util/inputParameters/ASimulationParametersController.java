package assignments.util.inputParameters;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

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
