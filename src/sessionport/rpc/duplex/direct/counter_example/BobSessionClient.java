package sessionport.rpc.duplex.direct.counter_example;

import port.sessionserver.SessionServerLauncher;
import util.trace.Tracer;
//import niotut.RspHandler;



public class BobSessionClient  {



public static void main(String[] args) {
	Tracer.showWarnings(false);
	Tracer.showInfo(false);
	
	(new ASessionPortCounterClientLauncher("Bob", "9093", "localhost", 
		
			SessionServerLauncher.SESSION_SERVER_ID,
			SessionServerLauncher.SESSION_SERVER_NAME 
			 )).launch();
  }
}

