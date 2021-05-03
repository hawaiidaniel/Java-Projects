import java.util.ArrayList;
import java.util.Scanner;
public class ReadTester {

	public static void main(String[] args) {
		
		ArrayList<Integer> test1 = new ArrayList<Integer>();
		Scanner in = new Scanner(System.in);
		
//		while (in.hasNextInt()) {
//			test1.add(in.nextInt());
//		}
		
//		for (int i = 0; i < test1.size(); i++) {
//			   System.out.print(test1.get(i));
//		}
		
//		while(in.hasNextInt()) {
//			System.out.println(in.nextInt());
//		}
		
		System.out.println("Please enter a line of integer values: ");
		if (in.hasNextLine())
		{
		    String line = in.nextLine();
		    Scanner scan = new Scanner(line);
		    while (scan.hasNextInt()) {
		        test1.add(scan.nextInt());
		    }
		}
	}

}
