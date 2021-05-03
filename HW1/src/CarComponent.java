import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import javax.swing.JComponent;
/**
   This component draws two car shapes.
*/
public class CarComponent extends JComponent
{  
   
   int TwoHead, HeadTail, TwoTail, roundnumber, frameheight, framewidth;
   int barheightOfTwoHead, barheightOfHeadTail, barheightOfTwoTail;
   double scale;
   public CarComponent() {
	  CoinTossSimulator CoinTester = new CoinTossSimulator();
	  roundnumber = 1000;
	  CoinTester.run(roundnumber);
	  TwoHead = CoinTester.getTwoHeads();	  
	  TwoTail = CoinTester.getTwoTails();	  
	  HeadTail = CoinTester.getHeadTails();
	  barheightOfTwoHead = (int) (TwoHead*100.0/roundnumber);
	  barheightOfHeadTail = (int) (HeadTail*100.0/roundnumber);
	  barheightOfTwoTail = (int) (TwoTail*100.0/roundnumber);
				
   }
	
   public void paintComponent(Graphics g)
   {  
	  

	
      Graphics2D g2 = (Graphics2D) g;
      frameheight = getHeight();
      framewidth = getWidth();
      scale = (frameheight - 100)/100.0;
      
      
      


      Bar bar1 = new Bar(frameheight - 50, framewidth/7, 100, barheightOfTwoHead, scale, Color.red,"Two Heads: "+TwoHead+"("+barheightOfTwoHead+"%)");
      Bar bar2 = new Bar(frameheight - 50, framewidth*3/7, 100, barheightOfHeadTail, scale, Color.green,"A Head and a Tail: "+HeadTail+"("+barheightOfHeadTail+"%)");
      Bar bar3 = new Bar(frameheight - 50, framewidth*5/7, 100, barheightOfTwoTail, scale, Color.blue,"Two Tails: "+TwoTail+"("+barheightOfTwoTail+"%)");


      bar1.draw(g2);    
      bar2.draw(g2);     
      bar3.draw(g2);
   }
}

