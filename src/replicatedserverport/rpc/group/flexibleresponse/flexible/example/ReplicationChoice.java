package replicatedserverport.rpc.group.flexibleresponse.flexible.example;
// good to extend it to support polling of servers that have crashed so that they can be reconnected
// also another extension could be to support tandem computing where an operation is not sent
// other servers until one server has executed it successfully
// these are  possible student extensions
// probably this enum will e called ReplicationReponse choice and the other params
// will be Delivery Sequence choice and yet another will be Re connect choice
public enum ReplicationChoice {
	ALL_ACCEPTING, // all resuts are received and given to client
	EARLIEST_ACCEPTING, // same as above except only one is given to client
	SINGLE_RESPONSE, // a single server responds, so server is aware of replication. Srever choice can vary
	LOCAL_RESPONSE, // a single local server is chosen
	PREFERRED_RESPONSE // Client identifies preferred choice, which can change

}
