package examples.nio.manager.print.noFactory;

import assignments.util.mainArgs.ClientArgsProcessor;

public class NIOManagerPrintClientLauncherNoFactory {
	public static void main(String[] args) {
		new AnNIOManagerPrintClient(ClientArgsProcessor.getNIOServerPort(args)).processInput();
	}

}
