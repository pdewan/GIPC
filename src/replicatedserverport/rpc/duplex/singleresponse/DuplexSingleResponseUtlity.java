package replicatedserverport.rpc.duplex.singleresponse;

import inputport.datacomm.ReceiveTrapper;
import inputport.datacomm.SendTrapper;
import inputport.datacomm.TrapperFactory;
import inputport.datacomm.duplex.DuplexClientInputPort;
import inputport.datacomm.duplex.DuplexServerInputPort;
import util.trace.Tracer;

public class DuplexSingleResponseUtlity {

	public static void supportSingleResponse(DuplexClientInputPort aClientPort ) {
			Tracer.info("Setting single response for client port " + aClientPort);
			SendTrapper oldSendTrapper = aClientPort.getSendTrapper();
			ReceiveTrapper oldReceiveTrapper = aClientPort.getReceiveTrapper();
			TrapperFactory trapperFactory = new ASingleResponseClientDuplexCallTrapperFactory();
			SendTrapper sendTrapper = trapperFactory.createSendTrapper(aClientPort, oldSendTrapper);
	//		ReceiveTrapper receiveTrapper = trapperFactory.createReceiveTrapper(aClientPort, oldReceiveTrapper.getDestination());
			// why destination, need to send it to received cal trapper
			ReceiveTrapper receiveTrapper = trapperFactory.createReceiveTrapper(aClientPort, oldReceiveTrapper);
	
			if (sendTrapper != null)
				aClientPort.setSendTrapper(sendTrapper);
			if (receiveTrapper != null)
				aClientPort.setReceiveTrapper(receiveTrapper);
		}

	public static void supportSingleResponse(TrapperFactory trapperFactory, DuplexServerInputPort aServerPort) {
	//		TrapperFactory trapperFactory = new ASingleResponseServerTrapperFactory();
			SendTrapper oldSendTrapper = aServerPort.getSendTrapper();
			ReceiveTrapper oldReceiveTrapper = aServerPort.getReceiveTrapper();
			SendTrapper sendTrapper = trapperFactory.createSendTrapper(aServerPort, oldSendTrapper);
			ReceiveTrapper receiveTrapper = trapperFactory.createReceiveTrapper(aServerPort, oldReceiveTrapper);
			aServerPort.setSendTrapper(sendTrapper);
			aServerPort.setReceiveTrapper(receiveTrapper);
		}

	public static void supportSingleResponse(DuplexServerInputPort aServerPort) {
			Tracer.info("Setting single response for server port " + aServerPort);
			TrapperFactory trapperFactory = new ASingleResponseServerDuplexTrapperFactory();
			supportSingleResponse(trapperFactory, aServerPort);
	//		SendTrapper oldSendTrapper = aServerPort.getSendTrapper();
	//		ReceiveTrapper oldReceiveTrapper = aServerPort.getReceiveTrapper();
	//		SendTrapper sendTrapper = trapperFactory.createSendTrapper(aServerPort, oldSendTrapper);
	//		ReceiveTrapper receiveTrapper = trapperFactory.createReceiveTrapper(aServerPort, oldReceiveTrapper);
	//		aServerPort.setSendTrapper(sendTrapper);
	//		aServerPort.setReceiveTrapper(receiveTrapper);
		}

}
