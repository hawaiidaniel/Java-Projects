// Name: Daniel Li
// USC NetID: lidw@usc.edu
// CS 455 PA1
// Fall 2018

import javax.swing.JFrame;
import java.util.Scanner;

//Here is the main class that prompts for the number of trials for the simulation and creates the frame with JFrame. 
//As the user inputs the valid number of trials, it calls a object of CoinSimComponent class. The CoinSimComponent
//also calls CoinTossSimulator and Bar classes to perform the simulation of coin toss and provide parameter for drawing the bars.
public class CoinSimViewer
{
   public static void main(String[] args)
   {
	  //creating in as object of Scanner
	  Scanner in = new Scanner(System.in);
	  int roundnumber; //declare roundnumber as integer
	  
	  //Here is checking the number that the uer inputs whether is valid or not. If the input number is less than or 
	  //equal to 0, it prints an error message. This function keeps running while the number is not valid.
	  do {
		  System.out.print("Please enter a number for CoinSim: ");
		  roundnumber = in.nextInt();
		  if (roundnumber <= 0) {
			  System.out.println("ERROR: Number entered must be greater than 0.");
		  }
		  //If the input is greater than 0, this function will break.
		  else {
			  break;
		  }
	  } while (roundnumber <= 0);
	  
	  //creating frame as object of JFrame.
	  JFrame frame = new JFrame();
	  //The following setup the size and title of the frame.
	  frame.setSize(800, 500);
      frame.setTitle("CoinSim");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      //creating component as object of CoinSimComponent with the users' input number
      CoinSimComponent component = new CoinSimComponent(roundnumber);
      //Add component to frame
      frame.add(component);

      frame.setVisible(true);
   }
}
