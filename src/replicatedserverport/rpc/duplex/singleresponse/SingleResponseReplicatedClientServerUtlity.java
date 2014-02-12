package replicatedserverport.rpc.duplex.singleresponse;

import inputport.datacomm.TrapperFactory;
import inputport.datacomm.duplex.DuplexServerInputPort;
import inputport.datacomm.group.GroupSendTrapper;
import inputport.datacomm.group.GroupServerInputPort;
import inputport.datacomm.group.GroupTrapperFactory;
import inputport.datacomm.group.object.ServerObjectGroupTrapperSelector;
import inputport.datacomm.simplex.object.ServerObjectTrapperSelector;
import inputport.rpc.duplex.ADuplexCallTrapperFactory;
import replicatedserverport.datacomm.duplex.ADuplexMultiToReplicatedTrapperFactory;
import replicatedserverport.datacomm.simplex.ClientMultiToReplicatedTrapperSelector;
import replicatedserverport.datacomm.simplex.MultiToReplicatedTrapperFactory;
import replicatedserverport.rpc.duplex.ReplicatedDuplexClientSerializableCallTrapperSelector;
import replicatedserverport.rpc.groupserver.singleresponse.ASingleResponseLatecomerRelayingGroupConnectionManagerFactory;
import replicatedserverport.rpc.groupserver.singleresponse.ASingleResponseServerGroupTrapperFactory;
import sessionport.datacomm.duplex.object.relayed.ObjectDuplexSPRelayedTrapperSelector;
import sessionport.datacomm.group.object.relayed.RelayingGroupConnectionManagerSelector;
import sessionport.datacomm.group.object.relayed.latecomer.ObjectGroupSPRelayedTrapperSelector;
import util.trace.Tracer;

public class SingleResponseReplicatedClientServerUtlity {

	public static void supportSingleResponse(GroupServerInputPort aServerPort) {
		Tracer.info(" Supporting single response for group server port " + aServerPort);
		GroupTrapperFactory trapperFactory = new ASingleResponseServerGroupTrapperFactory();
		DuplexSingleResponseUtlity.supportSingleResponse ((TrapperFactory) trapperFactory, (DuplexServerInputPort) aServerPort );
		SingleResponseReplicatedClientServerUtlity.supportSingleResponse(trapperFactory, aServerPort);		
	}

	public static void supportSingleResponse(GroupTrapperFactory trapperFactory, GroupServerInputPort aServerPort) {
			GroupSendTrapper oldSendTrapper = aServerPort.getGroupSendTrapper();
			GroupSendTrapper sendTrapper = trapperFactory.createGroupSendTrapper(aServerPort, oldSendTrapper);
			aServerPort.setGroupSendTrapper(sendTrapper);
		}

	//	public  void setTracing() {
	//		super.setTracing();
	////		Tracer.showInfo(true);
	//		Tracer.setKeyWordStatus(Tracer.ALL_KEYWORDS, true);
	//		// must change the package names
	////		Tracer.showInfo(true);
	////		Tracer.setKeyWordStatus(Tracer.ALL_KEYWORDS, false);
	////		Tracer.setKeyWordStatus("socketip", true);
	////		Tracer.setKeyWordStatus("socketdip", true);
	////		Tracer.setKeyWordStatus("sesrelaylategrpobj", true);
	////		Tracer.setKeyWordStatus("repsrvdupsingleresp", true);
	////		Tracer.setKeyWordStatus("repsrvgrpsingleresp", true);
	////		Tracer.setKeyWordStatus("sesrelaylate", true);
	//	}
	
	
	// these two routines should not be in this package, should be with the supports in groupserver
		public static void setSingleResponseGroupServerFactories() {			
			
			GroupTrapperFactory<Object, Object>  aSingleReponseFactory = 	new ASingleResponseServerGroupTrapperFactory();
			ServerObjectGroupTrapperSelector.getTrapperSelector().addGroupSendTrapperFactory(aSingleReponseFactory);
			ServerObjectGroupTrapperSelector.getTrapperSelector().addReceiveTrapperFactory(aSingleReponseFactory);		
		}
		public static void setSingleResponseRelayedGroupServerFactories() {
					
	
					GroupTrapperFactory<Object, Object>  aSingleReponseFactory = 	new ASingleResponseServerGroupTrapperFactory();
					ObjectGroupSPRelayedTrapperSelector.getTrapperSelector().addGroupSendTrapperFactory(aSingleReponseFactory);
					ObjectGroupSPRelayedTrapperSelector.getTrapperSelector().addReceiveTrapperFactory(aSingleReponseFactory);
					RelayingGroupConnectionManagerSelector.setRelayingGroupConnectionManagerFactory(
//					new ALatecomerRelayingGroupConnectionManagerFactory());
//					RelayingGroupConnectionManagerSelector.setRelayingGroupConnectionManagerFactory(
							new ASingleResponseLatecomerRelayingGroupConnectionManagerFactory());
				}
		
		public static void setSingleResponseRelayedDuplexServerFactories() {
			//		ReceiveTrapperFactory<Object, Object>  aReceiveTrapperFactory = new ASingle();
					
				TrapperFactory<Object, Object>  aSingleReponseFactory = 	new ASingleResponseServerDuplexTrapperFactory();
					
					
					ObjectDuplexSPRelayedTrapperSelector.getTrapperSelector().addSendTrapperFactory(aSingleReponseFactory);
					ObjectDuplexSPRelayedTrapperSelector.getTrapperSelector().addReceiveTrapperFactory(aSingleReponseFactory);		
				}
		
		public static void setSingleResponseDuplexServerFactories() {
			//		ReceiveTrapperFactory<Object, Object>  aReceiveTrapperFactory = new ASingle();
					
					TrapperFactory<Object, Object>  aSingleReponseFactory = 	new ASingleResponseServerDuplexTrapperFactory();
					ServerObjectTrapperSelector.getTrapperSelector().addSendTrapperFactory(aSingleReponseFactory);
					ServerObjectTrapperSelector.getTrapperSelector().addReceiveTrapperFactory(aSingleReponseFactory);
//					ServerObjectGroupTrapperSelector.getTrapperSelector().setGroupSendTrapperFactory(aSingleReponseFactory);
//					ServerObjectGroupTrapperSelector.getTrapperSelector().setReceiveTrapperFactory(aSingleReponseFactory);		
				}

		
			public static void setSingleResponseClientFactories() {
				setSingleResponseClientManagerFactory();
				setSingleResponseClientTrapperFactories();

			}
			
			public static void setRelayedSingleResponseClientFactories() {
				setSingleResponseClientManagerFactory();
				setRelayedSingleResponseClientTrapperFactories();

			}


			public static void setSingleResponseClientManagerFactory() {				
				ClientMessagesManagerSelector.setFactory(new ASingleResponseClientMessagesManagerFactory());			

			}
			public static void setSingleResponseClientTrapperFactories() {	
				TrapperFactory<Object, Object> trapperFactory = new ADuplexCallTrapperFactory();
//
//				ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//
//					setReceiveTrapperFactory(trapperFactory);
//				ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//					setSendTrapperFactory(trapperFactory);
//				ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//					setReceiveTrapperFactory(trapperFactory);
//				ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//					setSendTrapperFactory(trapperFactory);				
				
				TrapperFactory<Object, Object> singleResponseFactory = new ASingleResponseClientDuplexCallTrapperFactory();		
//				DuplexClientSerializableCallTrapperSelector.
				ReplicatedDuplexClientSerializableCallTrapperSelector.
				getTrapperSelector().
//				setReceiveTrapperFactory(singleResponseFactory);
				addReceiveTrapperFactory(singleResponseFactory);
//				DuplexClientSerializableCallTrapperSelector.
				ReplicatedDuplexClientSerializableCallTrapperSelector.
				getTrapperSelector().
				addSendTrapperFactory(singleResponseFactory);				
//				ReplicatedServerObjectTrapperSelector.
//				ClientMultiToReplicatedTrapperSelector.
//				getTrapperSelector().addReceiveTrapperFactory(new ADuplexMultiToReplicatedTrapperFactory());	
				MultiToReplicatedTrapperFactory multiToReplicatedFactory = new ADuplexMultiToReplicatedTrapperFactory();
//				ReplicatedServerObjectTrapperSelector.
				ClientMultiToReplicatedTrapperSelector.
				getTrapperSelector().setReceiveTrapperFactory(multiToReplicatedFactory);
				ClientMultiToReplicatedTrapperSelector.
				getTrapperSelector().setUniToGroupSendTrapperFactory(multiToReplicatedFactory);

			}
//			public static void setRelayedSingleResponseClientTrapperFactories() {
//				// client message manager is independent of input port
//				ClientMessagesManager aClientMessagesManager =  ClientMessagesManagerSelector.createClientMessagesManager();
//				TrapperFactory<Object, Object> upstreamFactory = new AnUpstreamSingleResponseClientDuplexCallTrapperFactory(aClientMessagesManager);	
//				TrapperFactory<Object, Object> downstreamFactory = new ADownstreamSingleResponseClientDuplexCallTrapperFactory(aClientMessagesManager);	
//				// the replicated port is up stream of relayer port
//				ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//				setReceiveTrapperFactory(upstreamFactory);
//				ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//				addSendTrapperFactory(upstreamFactory);	
//				// do we need this one now that we have a Replicated Serialisable Call Trapper Selector? I suspect not
//				// they conver logical to physical and back
////				ReplicatedServerObjectTrapperSelector.getTrapperSelector().addReceiveTrapperFactory(new AnAllAcceptingMultiToUniReceiveForwarderFactory());	
//				
//				// the replicated port is up stream of relayer port
//				DuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//				setReceiveTrapperFactory(downstreamFactory);
//				DuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//				addSendTrapperFactory(downstreamFactory);		
//				// do we need this one now that we have a Replicated Serialisable Call Trapper Selector? I suspect not
////				ReplicatedServerObjectTrapperSelector.
//				MultiToReplicatedTrapperFactory multiToReplicatedFactory = new ADuplexMultiToReplicatedTrapperFactory();
////				ReplicatedServerObjectTrapperSelector.
//				ClientMultiToReplicatedTrapperSelector.
//				getTrapperSelector().setReceiveTrapperFactory(multiToReplicatedFactory);
//				ClientMultiToReplicatedTrapperSelector.
//				getTrapperSelector().setUniToGroupSendTrapperFactory(multiToReplicatedFactory);
////				ClientMultiToReplicatedTrapperSelector.
////				getTrapperSelector().addReceiveTrapperFactory(new ADuplexMultiToReplicatedTrapperFactory());				
//
//			}
			// do not really need upstream and downstream single response it seems
			public static void setRelayedSingleResponseClientTrapperFactories() {
				// client message manager is independent of input port
//				ClientMessagesManager aClientMessagesManager =  ClientMessagesManagerSelector.createClientMessagesManager();
				TrapperFactory<Object, Object> upstreamFactory = new ASingleResponseClientDuplexCallTrapperFactory();	
//				TrapperFactory<Object, Object> downstreamFactory = new ADownstreamSingleResponseClientDuplexCallTrapperFactory(aClientMessagesManager);	
				// the replicated port is up stream of relayer port
				ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//				setReceiveTrapperFactory(upstreamFactory);
				addReceiveTrapperFactory(upstreamFactory); // add makes sure the same duplicate send trapper factory is picked
				ReplicatedDuplexClientSerializableCallTrapperSelector.getTrapperSelector().
				addSendTrapperFactory(upstreamFactory);	
				// do we need this one now that we have a Replicated Serialisable Call Trapper Selector? I suspect not
				// they conver logical to physical and back
//				ReplicatedServerObjectTrapperSelector.getTrapperSelector().addReceiveTrapperFactory(new AnAllAcceptingMultiToUniReceiveForwarderFactory());	
				
				// the replicated port is up stream of relayer port
//				DuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//				setReceiveTrapperFactory(downstreamFactory);
//				DuplexClientSerializableCallTrapperSelector.getTrapperSelector().
//				addSendTrapperFactory(downstreamFactory);		
				// do we need this one now that we have a Replicated Serialisable Call Trapper Selector? I suspect not
//				ReplicatedServerObjectTrapperSelector.
				MultiToReplicatedTrapperFactory multiToReplicatedFactory = new ADuplexMultiToReplicatedTrapperFactory();
//				ReplicatedServerObjectTrapperSelector.
				ClientMultiToReplicatedTrapperSelector.
				getTrapperSelector().setReceiveTrapperFactory(multiToReplicatedFactory);
				ClientMultiToReplicatedTrapperSelector.
				getTrapperSelector().setUniToGroupSendTrapperFactory(multiToReplicatedFactory);
//				ClientMultiToReplicatedTrapperSelector.
//				getTrapperSelector().addReceiveTrapperFactory(new ADuplexMultiToReplicatedTrapperFactory());
				RelayingGroupConnectionManagerSelector.setRelayingGroupConnectionManagerFactory(
//						new ALatecomerRelayingGroupConnectionManagerFactory());
//						RelayingGroupConnectionManagerSelector.setRelayingGroupConnectionManagerFactory(
								new ASingleResponseLatecomerRelayingGroupConnectionManagerFactory());

			}
			

}
