package port.trace.rpc;


import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import port.trace.buffer.TrapperBufferReceived;
import port.trace.buffer.TrapperBufferSendInitiated;
import port.trace.buffer.ClientNameAssociatedWithPort;
import port.trace.buffer.ClientNameLookedUp;
import port.trace.buffer.ClientNameSendInitiated;
import port.trace.buffer.nio.SocketChannelAccepted;
import port.trace.buffer.nio.SocketChannelConnectFinished;
import port.trace.buffer.nio.SocketChannelRead;
import port.trace.buffer.nio.SocketChannelWritten;
import port.trace.objects.ObjectReceived;
import port.trace.objects.ObjectSendInitiated;
import util.trace.ImplicitKeywordKind;
import util.trace.TraceableInfo;
import util.trace.Tracer;
import examples.mvc.rmi.duplex.ADistributedInheritingRMICounter;
import examples.mvc.rmi.duplex.DistributedRMICounter;
import examples.rmi.counter.simple.SimpleCounterClient;

public class RPCTraceUtility implements SimpleCounterClient{
	/**
	 * Do not change this, I will keep updating it and you will run into conflicts
	 * Make a copy if you want this changed
	 */
	public static void setTracing() {
		Tracer.showInfo(true);
		Tracer.setDisplayThreadName(true); 
		TraceableInfo.setPrintTraceable(true);
		TraceableInfo.setPrintSource(true);
		Tracer.setImplicitPrintKeywordKind(ImplicitKeywordKind.OBJECT_CLASS_NAME);
		
	
//		Tracer.setKeywordPrintStatus(CallInitiated.class, true);
		Tracer.setKeywordPrintStatus(CallReceived.class, true);
		Tracer.setKeywordPrintStatus(ProxyCreated.class, true);
		Tracer.setKeywordPrintStatus(ReceivedCallDequeued.class, true);
		Tracer.setKeywordPrintStatus(ReceivedCallEndedOld.class, true);
		Tracer.setKeywordPrintStatus(ReceivedCallQueued.class, true);
		Tracer.setKeywordPrintStatus(ReceivedObjectTransformed.class, true);
		Tracer.setKeywordPrintStatus(ReceivedReturnValueDequeued.class, true);
		Tracer.setKeywordPrintStatus(ReceivedReturnValueQueued.class, true);	
		Tracer.setKeywordPrintStatus(RegisteredObjectLookedUp.class, true);	
		Tracer.setKeywordPrintStatus(RemoteCallBlockedForReturnValue.class, true);
		Tracer.setKeywordPrintStatus(RemoteCallFinished.class, true);
		Tracer.setKeywordPrintStatus(RemoteCallInitiated.class, true);
		Tracer.setKeywordPrintStatus(RemoteCallReturnValueDetermined.class, true);			
		Tracer.setKeywordPrintStatus(SentObjectTransformed.class, true);


		



		Tracer.setKeywordPrintStatus(ObjectReceived.class, true);
		Tracer.setKeywordPrintStatus(ObjectSendInitiated.class, true);
		Tracer.setKeywordPrintStatus(ClientNameSendInitiated.class, true);
		Tracer.setKeywordPrintStatus(ClientNameAssociatedWithPort.class, true);
		Tracer.setKeywordPrintStatus(ClientNameLookedUp.class, true);


		Tracer.setKeywordPrintStatus(TrapperBufferSendInitiated.class, true);
		Tracer.setKeywordPrintStatus(TrapperBufferReceived.class, true);
		
		Tracer.setKeywordPrintStatus(SocketChannelAccepted.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelConnectFinished.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelWritten.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelRead.class, true);


	}

}
