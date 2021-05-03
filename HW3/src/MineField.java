// Name: Daniel Li
// USC NetID: lidw
// CS 455 PA3
// Fall 2018


/** 
   MineField
      class with locations of mines for a game.
      This class is mutable, because we sometimes need to change it once it's created.
      mutators: populateMineField, resetEmpty
      includes convenience method to tell the number of mines adjacent to a location.
 */
import java.util.Random;
public class MineField {
   
   // <put instance variables here>
   /*create multiple instance variables*/
   private boolean[][] mineField;
   private int row;
   private int col;
   private int numMines = 0;

   /**
      Create a minefield with same dimensions as the given array, and populate it with the mines in the array
      such that if mineData[row][col] is true, then hasMine(row,col) will be true and vice versa.  numMines() for
      this minefield will corresponds to the number of 'true' values in mineData.
    * @param mineData  the data for the mines; must have at least one row and one col.
    */
   public MineField(boolean[][] mineData) {
	   /*get the given mineData, the number of row and column of the array*/
	   mineField = mineData;
	   row = mineData.length;
	   col = mineData[0].length;

	   /*count how many mines are in the mineField*/
	   for(int i = 0; i < row; i++) {
			for(int j = 0; j < col; j++) {
				if(mineField[i][j]) {
					numMines++;
				}
			}
		}
   }
   
   
   /**
      Create an empty minefield (i.e. no mines anywhere), that may later have numMines mines (once 
      populateMineField is called on this object).  Until populateMineField is called on such a MineField, 
      numMines() will not correspond to the number of mines currently in the MineField.
      @param numRows  number of rows this minefield will have, must be positive
      @param numCols  number of columns this minefield will have, must be positive
      @param numMines   number of mines this minefield will have,  once we populate it.
      PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3 of total number of field locations). 
    */
   public MineField(int numRows, int numCols, int numMines) {
      
	  /*get the number of rows, columns and mines as given*/
	  this.row = numRows;
	  this.col = numCols;
	  this.numMines = numMines;
	  /*initiate the mineField with no mines*/
	  mineField = blankField(numRows, numCols);
	
   }
   

   /**
      Removes any current mines on the minefield, and puts numMines() mines in random locations on the minefield,
      ensuring that no mine is placed at (row, col).
      @param row the row of the location to avoid placing a mine
      @param col the column of the location to avoid placing a mine
      PRE: inRange(row, col)
    */
   public void populateMineField(int row, int col) {
	  /*reset the mineField with no mines*/
	  resetEmpty();
	  
	  for(int i = 0; i < this.numMines; i++) {
		  /*put mines in the mineField randomly*/
		  mineField = randomLoc(row, col, mineField.length, mineField[0].length, this.mineField);
	  }
	  
	  
   }
   
   
   /**
      Reset the minefield to all empty squares.  This does not affect numMines(), numRows() or numCols()
      Thus, after this call, the actual number of mines in the minefield does not match numMines().  
      Note: This is the state the minefield is in at the beginning of a game.
    */
   public void resetEmpty() {
	  /*reset the mineField with no mines*/
      mineField = blankField(this.row, this.col);
   }

   
  /**
     Returns the number of mines adjacent to the specified mine location (not counting a possible 
     mine at (row, col) itself).
     Diagonals are also considered adjacent, so the return value will be in the range [0,8]
     @param row  row of the location to check
     @param col  column of the location to check
     @return  the number of mines adjacent to the square at (row, col)
     PRE: inRange(row, col)
   */
   public int numAdjacentMines(int row, int col) {
	    /*create a integer variable*/
	    int adjacent = 0;
	    /*count how many mines are there surrounding the square*/
		for(int i = row-1; i < row+2; i++) {
			for(int j = col-1; j < col+2; j++) {
				if(inRange(i, j)) {
					/*skip the loop if the location is the square itself*/
					if(i==row && j ==col) {
						continue;
					}
					else {
						/*when detect mine, adjacent plus 1*/
						if(hasMine(i, j)) {
							adjacent++;
						}
					}
				}
				
			}
		}
		/*return the number of adjacent mines*/
		return adjacent;
   }
   
   
   /**
      Returns true iff (row,col) is a valid field location.  Row numbers and column numbers
      start from 0.
      @param row  row of the location to consider
      @param col  column of the location to consider
      @return whether (row, col) is a valid field location
   */
   public boolean inRange(int row, int col) {
	   /*if the given row and col is less than 0 or greater than the maximum number, return false*/
	   if(row < 0 || row >= this.row || col < 0 || col >= this.col) {
		   return false;
	   }
	   /*return true if it's valid*/
	   else {
		   return true;
	   }
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */  
   public int numRows() {
	  /*return the number of rows in the field*/
      return row;       
   }
   
   
   /**
      Returns the number of rows in the field.
      @return number of rows in the field
   */    
   public int numCols() {
	  /*return the number of columns in the field*/
      return col;    
   }
   
   
   /**
      Returns whether there is a mine in this square
      @param row  row of the location to check
      @param col  column of the location to check
      @return whether there is a mine in this square
      PRE: inRange(row, col)   
   */    
   public boolean hasMine(int row, int col) {
	  /*return true if the square has mine*/ 
	  if(mineField[row][col]) {
    	  return true;
      }
	  /*return false if not*/
      else {
    	  return false;
      }
   }
   
   
   /**
      Returns the number of mines you can have in this minefield.  For mines created with the 3-arg constructor,
      some of the time this value does not match the actual number of mines currently on the field.  See doc for that
      constructor, resetEmpty, and populateMineField for more details.
    * @return
    */
   public int numMines() {
	  /*return the total number of mines in the field*/
      return numMines;  
   }

   
   // <put private methods here>
   /**
   Returns a mineField with mines randomly set. If the location of the field is set mine previously or
   is the square the player open in the first step, it needs to find another square to set mine.
    * @param userRow the row of the location that the player first open
    * @param userCol the column of the location that the player first open
    * @param numRows the number of rows in this field
    * @param numCols the number of columns in this field
    * @param mineField the given field
    * @return mineField return the field after mine set
    */
   public boolean[][] randomLoc(int userRow, int userCol, int numRows, int numCols, boolean[][] mineField ) {
	   
	   /*create a Random instance rand*/
	   Random rand = new Random();
	   /*create a random integer x [0,numRows)*/
	   int x = rand.nextInt(numRows);
	   /*create a random integer y [0,numCols)*/
	   int y = rand.nextInt(numCols);
	   /*if the sqaure is set mine previously or the player first open up*/
	   if(mineField[x][y] == true || (x == userRow && y == userCol)) {
		   /*run again to look for another location to set mine*/
		   return randomLoc(userRow, userCol, numRows, numCols, mineField);
	   }
	   else {
		   /*if not, then set mine here and return the field*/
		   mineField[x][y] = true;
		   return mineField;
	   }
   }
   
   /**
    * @param row the number of row in the field
    * @param col the number of column in the field
    * @return a field with no mines
    */
   public boolean[][] blankField(int row, int col){
	   /*create a 2D array field with size row*col */
	   boolean[][] mat = new boolean[row][col];
	   for(int i = 0; i < row; i++) {
		   for(int j = 0; j < col; j++) {
			   /*set to no mine for all squares in the field*/
			   mat[i][j] = false;
		   }
	   }
	   /*return the field*/
	   return mat;
   }
         
}

