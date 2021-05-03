
public class MineFieldTester {
	
	//private static MineField x = new MineField(4,4,3);
	private static boolean[][] smallMineField = 
		    {{false, false, false, false}, 
		    {true, false, false, false}, 
		     {false, true, true, false},
		     {false, true, false, true}};
	private static MineField y = new MineField(smallMineField);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		y.populateMineField(1, 2);
		for(int i = 0; i < 4; i++) {
			for(int j = 0; j < 4; j++) {
				//System.out.print(y.mineField[i][j]+" ");
			}
			System.out.println();
		}
	}

}
