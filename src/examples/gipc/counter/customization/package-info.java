
/**
 * @author dewan
 *
 */
/**
 * Make sure you run and understand the code in the examples.gipc.counter.layers 
 * before you process this package 
 * 
 * Package showing how you can override internal classes involved in RPC
 * communication by setting appropriate factories to replace the internal
 * classes with subclasses of them that override relevant methods by tracing
 * their invocation.
 * 
 * Run the implemented server and clients as follows:
 * 
 * 1. Run ACustomCounterServerLauncher
 * 2. Run ACustomCounterClient1Launcher
 * 3. Run ACustomCounterClient2Launcher
 * 
 * The output is similar to the output of the classes in examples.gipc.counter.layers 
 * except that installed custom internal RPC-processing classes now trace the 
 * internal communication through additional print messages. 
 * 
 * Look all of the code in the package starting with the server and client and
 * ATracingFactorySetter, which sets the factories.
 */
package examples.gipc.counter.customization;