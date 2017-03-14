package port.trace.buffer;


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

public class BufferTraceUtility implements SimpleCounterClient{
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
		Tracer.setKeywordPrintStatus(BufferChannelConnectFailure.class, true);
		Tracer.setKeywordPrintStatus(BufferChannelConnectFinished.class, true);
		Tracer.setKeywordPrintStatus(BufferChannelConnectInitiated.class, true);
		Tracer.setKeywordPrintStatus(BufferChannelDisconnected.class, true);
		Tracer.setKeywordPrintStatus(BufferChannelDisconnectInitiated.class, true);
		Tracer.setKeywordPrintStatus(BufferClientChannelLocallyConnected.class, true);
		Tracer.setKeywordPrintStatus(BufferLocalSendFinished.class, true);
		Tracer.setKeywordPrintStatus(BufferLocalSendInitiated.class, true);	
		Tracer.setKeywordPrintStatus(BufferReceived.class, true);	
		Tracer.setKeywordPrintStatus(BufferReplyInitiated.class, true);
		Tracer.setKeywordPrintStatus(BufferSendFinished.class, true);
		Tracer.setKeywordPrintStatus(BufferSendInitiated.class, true);
		Tracer.setKeywordPrintStatus(BufferSendToUnconnectedChannelIgnored.class, true);			
		Tracer.setKeywordPrintStatus(ClientNameAssociatedWithPort.class, true);
		Tracer.setKeywordPrintStatus(ClientNameLookedUp.class, true);
		Tracer.setKeywordPrintStatus(ClientNameSendInitiated.class, true);	
		Tracer.setKeywordPrintStatus(DuplicateBufferChannelConnectIgnored.class, true);	
		Tracer.setKeywordPrintStatus(ReplyDestinationAssociatedWithPort.class, true);	
		Tracer.setKeywordPrintStatus(TrapperBufferReceived.class, true);
		Tracer.setKeywordPrintStatus(TrapperBufferSendFinished.class, true);
		Tracer.setKeywordPrintStatus(TrapperBufferSendInitiated.class, true);	
		Tracer.setKeywordPrintStatus(BufferReceived.class, true);
		

//
//
//
//		
//
//
//
//		Tracer.setKeywordPrintStatus(ObjectReceived.class, true);
//		Tracer.setKeywordPrintStatus(ObjectSendInitiated.class, true);
//		Tracer.setKeywordPrintStatus(ClientNameSendInitiated.class, true);
//		Tracer.setKeywordPrintStatus(ClientNameAssociatedWithPort.class, true);
//		Tracer.setKeywordPrintStatus(ClientNameLookedUp.class, true);
//
//
//		Tracer.setKeywordPrintStatus(TrapperBufferSendInitiated.class, true);
//		Tracer.setKeywordPrintStatus(TrapperBufferReceived.class, true);
//		
//		Tracer.setKeywordPrintStatus(SocketChannelAccepted.class, true);
//		Tracer.setKeywordPrintStatus(SocketChannelConnectFinished.class, true);
//		Tracer.setKeywordPrintStatus(SocketChannelWritten.class, true);
//		Tracer.setKeywordPrintStatus(SocketChannelRead.class, true);


	}

}
