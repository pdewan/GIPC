package util.interactiveMethodInvocation;

import assignments.util.inputParameters.SimulationParametersListener;

public interface SimulationParametersController extends CommandToMethodCallTranslator{

	public void addSimulationParameterListener(
			SimulationParametersListener aListener);
	/**
	 * Repeatedly process user commands until the user quits. In response to
	 * each command, call the corresponding user-defined method.
	 */
	public void processCommands();

}