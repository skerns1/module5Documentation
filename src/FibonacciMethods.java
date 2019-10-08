import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.SwingUtilities;

public class FibonacciMethods {
	
	//Get user input, get run speeds, and then run JFreeChart Graph with data made from XYLineChart Object
	public static void main(String[] args) {
	
	//Lists for holding runtimes
	ArrayList<Long> IterativeTimes = new ArrayList<Long>();
	ArrayList<Long> RecursiveTimes = new ArrayList<Long>();	
	
	//Get amount of terms to get run times for from user
	int terms = getInput();
	
	//Populate the lists with runtimes
	FibonacciRunSpeeds(terms, IterativeTimes, RecursiveTimes);
	
	//Create and Run an XYLineChart in main
	SwingUtilities.invokeLater(new Runnable() {
        @Override
        public void run() {
            new XYLineChart(IterativeTimes, RecursiveTimes).setVisible(true);
        }
	});
	
	}//end main
	
	//Iterative Fibonacci for the parameter terms number of sequences
	public static long FibonacciIterative(int terms) {
		int n1 = 0;
		int n2 = 1;
		int n3 = 0;
		long startTime = System.nanoTime();
		if (terms <= 1)
			return (System.nanoTime() - startTime);
		for (int i = 0; i < terms; i++) {
			n3 = n1 + n2;
			n1 = n2;
			n2 = n3;
		        }//end for loop
		return (System.nanoTime() - startTime);
	}//end FibonacciIterative
	
	//Recursive Fibonacci for the parameter terms number of sequences
	public static int FibonacciRecursive(int terms) {
		if (terms <= 1)
			return terms;
		return FibonacciRecursive(terms - 1) + FibonacciRecursive(terms - 2);
		}//end FibonacciRecursive
	
	//Gather Run Speeds of iterative and recursive fibonacci sequences for the inputted terms
	public static void FibonacciRunSpeeds(int terms, ArrayList<Long> iterative, ArrayList<Long> recursive) {
		long startTime = 0;
		int i = 0;	
		
		//Run Iterative Sequences and collect times for each term in a list
		for (i = 0; i < terms; i++) {
			iterative.add(FibonacciIterative(i));
			}//end for loop
		
		//Run Fibonacci Sequences and collect times for each term in a list
		for (i = 0; i < terms; i++) {
			startTime = System.nanoTime();
			FibonacciRecursive(i);
			recursive.add(System.nanoTime() - startTime);
			}//end for loop
		
	}//end FibonacciMethods
	
	//Get input from user
	public static int getInput() {
		String result;
		System.out.print("Please enter the number of terms you'd like to see the rune time of: ");
		Scanner s = new Scanner(System.in);
		result = s.nextLine();
		while (!isNumeric(result) || Integer.parseInt(result) < 0) {
			System.out.println("Please enter an integer greater than 0");
			result = s.nextLine();
		}//end while loop
		return Integer.parseInt(result);
	}//end getInput
		
	//Check if input is an integer
	public static boolean isNumeric(String strNum) {
	    try {
	        int i = Integer.parseInt(strNum);
	    }//end try 
	    catch (NumberFormatException | NullPointerException nfe) {
	        return false;
	    }//end catch
	    return true;
	}//end isNumber
	
}//end FibonacciMethods
