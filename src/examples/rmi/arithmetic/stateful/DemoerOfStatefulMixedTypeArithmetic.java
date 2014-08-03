package examples.rmi.arithmetic.stateful;

import examples.rmi.arithmetic.AMixedTypeArithmeticServerLauncher;
import bus.uigen.pipe.MainClassLaunchingUtility;
// this is a distrbuted implementation of a CS 1 (UNC Comp 110) first assignment submission described below
/******************************************************************
 * Program or Assignment #: Assignment1
 *
 * Programmer: Jacob
 *
 * Due Date: Tuesday, Jan. 28
 *
 * COMP110-002, Spring 2014       Instructor: Prof. Jay Aikat
 *
 * Pledge: I have neither given nor received unauthorized aid
 *         on this program. 
 *
 * Description: Insert a brief paragraph describing the program
 *
 * Input: Insert a brief description of user inputs, or "None" if
 *        there is no user input
 *
 * Output: Insert a brief description of the program output
 *
 ******************************************************************/
public class DemoerOfStatefulMixedTypeArithmetic {
	public static void main(String args[]) {
		demo();
	}
	
	public static void demo() {
//		String currentDir = System.getProperty("user.dir");
//        System.out.println("Current dir using System:" +currentDir);
		
		Class[] classes = {
				DistributedRMIRegistryStarter.class,
				AStatefulMixedTypeArithmeticServerLauncher.class,
				AMixedTypeArithmeticIntSetterLauncher.class,
				AMixedTypeArithmeticDoubleSetterAndDisplayerLauncher.class,
//				AMixedTypeArithmeticServerLauncher.class
				
		};
		MainClassLaunchingUtility.createInteractiveLauncher(classes);
	}
	
	

}
