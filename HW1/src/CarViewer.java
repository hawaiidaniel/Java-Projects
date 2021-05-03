import javax.swing.JFrame;
import java.lang.Math;

public class CarViewer
{
   public static void main(String[] args)
   {
	  
	  JFrame frame = new JFrame();

      frame.setSize(800, 500);
      frame.setTitle("Two cars");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

      CarComponent component = new CarComponent();
      frame.add(component);

      frame.setVisible(true);
   }
}
