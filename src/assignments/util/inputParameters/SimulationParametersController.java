package assignments.util.inputParameters;

public interface SimulationParametersController extends CommandToMethodCallTranslator{

	public void addSimulationParameterListener(
			SimulationParametersListener aListener);
	public void processCommands();

}