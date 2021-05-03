// Name: Daniel Li
// USC NetID: lidw@usc.edu
// CS 455 PA1
// Fall 2018

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
/**
   This CoinSimComponent class uses the results from simulation and setup the frame dimensions
*/
public class CoinSimComponent extends JComponent
{  
   
   //instance variables declaration
   private int TwoHead, HeadTail, TwoTail, barheightOfTwoHead, barheightOfHeadTail, barheightOfTwoTail;
   
   //Constructor declaration of class
   public CoinSimComponent(int roundnumber) {
	   
	  //creating CoinTester here from CoinTossSimulator class with input "roundnumber" from users
	  CoinTossSimulator CoinTester = new CoinTossSimulator();
	  CoinTester.run(roundnumber);
	  
	  //let TwoHead,TwoTail and HeadTail to be the results from performing the simulation and calling the responding methods
	  TwoHead = CoinTester.getTwoHeads();	  
	  TwoTail = CoinTester.getTwoTails();	  
	  HeadTail = CoinTester.getHeadTails();
	  
	  //setup the percentage for each case and let them to be integers
	  barheightOfTwoHead = (int) (TwoHead*100.0/roundnumber);
	  barheightOfHeadTail = (int) (HeadTail*100.0/roundnumber);
	  barheightOfTwoTail = (int) (TwoTail*100.0/roundnumber);
				
   }
   
   //Here draws the bar for each case with the Bar class, g is graphic parameter
   public void paintComponent(Graphics g)
   {  
	  
      Graphics2D g2 = (Graphics2D) g;
      
      //declare frameheight and framewidth as the height and width of the frame
      int frameheight = getHeight();
      int framewidth = getWidth();
      int vb = 50; //declare vb to be 50 as the vertical buffer 
      int bw  = 100; //declare bw to be 100 as the bar width
      double scale = (frameheight - 2*vb)/100.0; //declare scale as the percentage of the bar height over frame height
      
      //creating  3 bars using Bar class for each case
      Bar bar1 = new Bar(frameheight - vb, framewidth/7, bw, barheightOfTwoHead, scale, Color.red,"Two Heads: "+TwoHead+"("+barheightOfTwoHead+"%)");
      Bar bar2 = new Bar(frameheight - vb, framewidth*3/7, bw, barheightOfHeadTail, scale, Color.green,"A Head and a Tail: "+HeadTail+"("+barheightOfHeadTail+"%)");
      Bar bar3 = new Bar(frameheight - vb, framewidth*5/7, bw, barheightOfTwoTail, scale, Color.blue,"Two Tails: "+TwoTail+"("+barheightOfTwoTail+"%)");
      
      //draw 3 bars
      bar1.draw(g2);    
      bar2.draw(g2);     
      bar3.draw(g2);
   }
}

