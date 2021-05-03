import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Hello {

	public static void main(String[] args) {
		/*
		Calendar c = new GregorianCalendar();
		c.set(1995, 0, 20);
		Date da = c.getTime();
		System.out.println(da);
		
		c.add(c.DAY_OF_MONTH, 20);
		Date d = c.getTime();
		System.out.println(d);  */
		
		
		Scanner in = new Scanner(System.in);
		System.out.print("Enter your birth month [1..12]: ");
		int birmonth = in.nextInt();
		System.out.print("Enter your birth day of month: ");
		int birday = in.nextInt();
		System.out.print("Enter your birth year [4-digit year]: ");
		int biryear = in.nextInt();
		
		Calendar now = Calendar.getInstance();
		int year = now.get(Calendar.YEAR);
		
		Calendar birth = new GregorianCalendar();
		//birth.set(year, month-1, day);
		birth.set(year, birmonth-1, birday, 0, 0);
		
//		System.out.println(birth.getTime());
//		System.out.println(now.getTime());
//		System.out.println(now.after(birth));
		
		if (now.after(birth)) {
			
			System.out.println("Your birthday has already happened this year.");
			System.out.println("You're "+(now.get(Calendar.YEAR)-biryear)+" years old.");
			
		} else {
			
			System.out.println("Your birthday has not yet happened this year.");
			System.out.println("You're "+(now.get(Calendar.YEAR)-biryear-1)+" years old.");
			
		}
		
		
		int month = now.get(Calendar.MONTH) + 1;
		int day = now.get(Calendar.DAY_OF_MONTH);
		if (month == birmonth & day == birday) {
			System.out.println("Your birthday is today!");
		}
		
	}

}
