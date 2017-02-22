package examples.gipc.counter.simple;


import inputport.rpc.ACachingAbstractRPCProxyInvocationHandler;
import inputport.rpc.GIPCLocateRegistry;
import inputport.rpc.GIPCRegistry;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import port.trace.ByteBufferReceived;
import port.trace.ByteBufferSendInitiated;
import port.trace.ClientNameAssociatedWithPort;
import port.trace.ClientNameLookedUp;
import port.trace.ClientNameSendInitiated;
import port.trace.ObjectReceived;
import port.trace.ObjectSendInitiated;
import port.trace.nio.SocketChannelAccepted;
import port.trace.nio.SocketChannelConnectFinished;
import port.trace.nio.SocketChannelRead;
import port.trace.nio.SocketChannelWritten;
import port.trace.rpc.ProxyCreated;
import port.trace.rpc.ReceivedCallDequeued;
import port.trace.rpc.CallGenerated;
import port.trace.rpc.CallInitiated;
import port.trace.rpc.ReceivedCallQueued;
import port.trace.rpc.CallReceived;
import port.trace.rpc.ReceivedCallEndedOld;
import port.trace.rpc.ReceivedObjectTransformed;
import port.trace.rpc.ReceivedReturnValueDequeued;
import port.trace.rpc.ReceivedReturnValueQueued;
import port.trace.rpc.RegisteredObjectLookedUp;
import port.trace.rpc.RemoteCallBlockedForReturnValue;
import port.trace.rpc.RemoteCallFinished;
import port.trace.rpc.RemoteCallInitiated;
import port.trace.rpc.RemoteCallReturnValueDetermined;
import port.trace.rpc.ReturnValueDequeuedOld;
import port.trace.rpc.ReturnValueQueuedOld;
import port.trace.rpc.SentObjectTransformed;
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


		Tracer.setKeywordPrintStatus(ByteBufferSendInitiated.class, true);
		Tracer.setKeywordPrintStatus(ByteBufferReceived.class, true);
		
		Tracer.setKeywordPrintStatus(SocketChannelAccepted.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelConnectFinished.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelWritten.class, true);
		Tracer.setKeywordPrintStatus(SocketChannelRead.class, true);


	}

}