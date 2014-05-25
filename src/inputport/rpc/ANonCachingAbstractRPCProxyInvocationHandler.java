package inputport.rpc;

import inputport.rpc.simplex.SimplexRPC;

import java.lang.reflect.Method;

import util.remote.InvocationHandlerWithProperties;

public abstract class ANonCachingAbstractRPCProxyInvocationHandler implements
		InvocationHandlerWithProperties{
	protected SimplexRPC rpcInputPort;
	protected Class remoteType;
	String name;
	protected String destination;
	boolean replayMode = false; 


//	protected int outstandingCallsInReplayMode = 0;

	
	// distinguish between efficient and not efficient handler
	public ANonCachingAbstractRPCProxyInvocationHandler(SimplexRPC anRPCPort,
			String aDestination, Class aType, String aName) {
		rpcInputPort = anRPCPort;
		destination = aDestination;
		remoteType = aType;
		name = aName;
//		TraceableBus.addTraceableListener(this);

		
		
	}
	
	
	public Class getProxyTargetClass() {
		return remoteType;
	}
	
	public String getProxyDestination() {
		return destination;
	}
	
	public String getProxyName() {
		return name;
	}
	
	public void setProxyDestination(String aDestination) {
		 destination = aDestination;
	}
	
	public void setProxyName(String aName) {
		 name = aName;
	}

	public SimplexRPC getRPCInputPort() {
		return rpcInputPort;
	}
	
	public void setRPCInputPort (SimplexRPC anRPCInputPort) {
		rpcInputPort = anRPCInputPort;
	}

	protected abstract Object call(String remoteEndPoint, String name,
			Method method, Object[] args);

	protected abstract Object call(String remoteEndPoint, Method method,
			Object[] args);

	protected abstract Object call(String remoteEndPoint, Class type,
			Method method, Object[] args);

	
	public Object invoke(Object arg0, Method method, Object[] args) {


		try {
			if (name != null)
				return call(destination, name, method, args);
			else if (remoteType != null) {
				return call(destination, remoteType, method, args);
			} else {
				return call(destination, method, args); // will this ever be executed?
			}
		} catch (RuntimeException re) {
			throw re;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
	
	
		// the non replay stuff should be in superclass as it has nothing to do with caching
	// actually it should be done by a single object, whereas an invocation handler is created for each proxy
//				public  void newEvent(Exception aTraceable) {
//					// actually should see if the message was really sent or not, and not just delayed or buffered - or should one?
//					// if delayed, the toString message will also be delayed
////					if (aTraceable instanceof ByteBufferSendInfo || aTraceable instanceof ByteBufferReceiveInfo)
//					if ( aTraceable instanceof CallReceived) {
//						if (replayMode) {
//							outstandingCallsInReplayMode++;
//							Tracer.info(this, "Incremented outstandingCallsInReplayMode to: " + outstandingCallsInReplayMode);
//
//						}
//
//					} else if (aTraceable instanceof ReceivedCallEnded) {
//						if (replayMode) {
//							outstandingCallsInReplayMode--;
//							Tracer.info(this, "Decremented outstandingCallsInReplayMode to: " + outstandingCallsInReplayMode);
//						}
//					}
//					else if (aTraceable instanceof ReplayStartInfo ) {
//						replayMode = true;
//					} else if (aTraceable instanceof AsyncReplayEndInfo) {
//						// this does not work because the replay happens 
//						// asynchronously
//						replayMode = false; 
//						if 	(outstandingCallsInReplayMode == 0) {
//							AsyncReplayEndInfo asyncReplayEndInfo = (AsyncReplayEndInfo) aTraceable;
//							SyncReplayEndInfo.newCase(asyncReplayEndInfo.getFinder(), asyncReplayEndInfo.getReplayMessageList());
//						}
//
//
//					} 
//					
//				}
			

}
