package sessionport.rpc.duplex.direct.counter_example;

import port.sessionserver.SessionServerLauncher;
import util.trace.Tracer;
//import niotut.RspHandler;



public class AliceSessionClient  {



public static void main(String[] args) {
	Tracer.showWarnings(false);
	Tracer.showInfo(false);
	
	(new ASessionPortCounterClientLauncher("Alice", "9092", "localhost", 
		
			SessionServerLauncher.SESSION_SERVER_ID,
			SessionServerLauncher.SESSION_SERVER_NAME 
			 )).launch();
  }
}

