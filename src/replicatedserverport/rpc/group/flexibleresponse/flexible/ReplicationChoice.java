package replicatedserverport.rpc.group.flexibleresponse.flexible;
// good to extend it to support polling of servers that have crashed so that they can be reconnected
// also another extension could be to support tandem computing where an operation is not sent
// other servers until one server has executed it successfully
// these are  possible student extensions
// probably this enum will e called ReplicationReponse choice and the other params
// will be Delivery Sequence choice and yet another will be Re connect choice
// can also support hyrbid architecture
// can we have servers talk to each other by making them members rather than servers only
// or they can have their own server
// can also have students implement broadcast methods using this scheme
// only forwardng methods that are annotated as broadcast
public enum ReplicationChoice {
	ALL_ACCEPTING, // all resuts are received and given to client
	EARLIEST_ACCEPTING, // same as above except only one is given to client
	SINGLE_RESPONSE, // a single server responds, so server is aware of replication. Srever choice can vary
	
	LOCAL_RESPONSE, // a single fixed server is chosen, local is a misnomer, it is a speical clase. Client Server Mapping fixes it
					// client server mapping is known only to server
	
	PREFERRED_RESPONSE // Client identifies preferred choice, set by the client server mapping, which can change, 
						// so it seems like an extension of single response which the students rae supposed to change
						// client srever mapping is known to both client and server as client needs to choose preferred when all are available

}
