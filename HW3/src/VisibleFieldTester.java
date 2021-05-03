
public class VisibleFieldTester {
	
//	private static MineField x = new MineField(4,4,3);
//	private static VisibleField v = new VisibleField(x);
   private static boolean[][] smallMineField = 
	      {{false, false, false, false}, 
	       {true, false, false, false}, 
	       {false, true, true, false},
	       {false, true, false, true}};
   
	public static void main(String[] args) {
		MineField x = new MineField(smallMineField);

		
		VisibleField v = new VisibleField(x);

//		v.resetGameDisplay();
//		v.resetGameDisplay();
	}

}
