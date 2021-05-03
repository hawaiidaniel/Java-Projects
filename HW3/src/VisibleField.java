// Name: Daniel Li
// USC NetID: lidw
// CS 455 PA3
// Fall 2018


/**
  VisibleField class
  This is the data that's being displayed at any one point in the game (i.e., visible field, because it's what the
  user can see about the minefield), Client can call getStatus(row, col) for any square.
  It actually has data about the whole current state of the game, including  
  the underlying mine field (getMineField()).  Other accessors related to game status: numMinesLeft(), isGameOver().
  It also has mutators related to moves the player could do (resetGameDisplay(), cycleGuess(), uncover()),
  and changes the game state accordingly.
  
  It, along with the MineField (accessible in mineField instance variable), forms
  the Model for the game application, whereas GameBoardPanel is the View and Controller, in the MVC design pattern.
  It contains the MineField that it's partially displaying.  That MineField can be accessed (or modified) from 
  outside this class via the getMineField accessor.  
 */
public class VisibleField {
   // ----------------------------------------------------------   
   // The following public constants (plus numbers mentioned in comments below) are the possible states of one
   // location (a "square") in the visible field (all are values that can be returned by public method 
   // getStatus(row, col)).
   
   // Covered states (all negative values):
   public static final int COVERED = -1;   // initial value of all squares
   public static final int MINE_GUESS = -2;
   public static final int QUESTION = -3;

   // Uncovered states (all non-negative values):
   public static final int UNCOVERED = 1;
   
   // values in the range [0,8] corresponds to number of mines adjacent to this square
   
   public static final int MINE = 9;      // this loc is a mine that hasn't been guessed already (end of losing game)
   public static final int INCORRECT_GUESS = 10;  // is displayed a specific way at the end of losing game
   public static final int EXPLODED_MINE = 11;   // the one you uncovered by mistake (that caused you to lose)
   // ----------------------------------------------------------   
  
   // <put instance variables here>
   /*create multiple instance variables*/
   private int visibleRow;
   private int visibleCol;
   private int[][] vField;
   private int numMine;
   private int numGuess = 0;
   private int numUncovered = 0;
   private int numTotal;
   MineField mf;

   /**
      Create a visible field that has the given underlying mineField.
      The initial state will have all the mines covered up, no mines guessed, and the game
      not over.
      @param mineField  the minefield to use for for this VisibleField
    */
   public VisibleField(MineField mineField) {
	   /*let mf = mineField, get the number of row,columns,total squares and mines*/
	   mf = mineField;
	   visibleRow = mf.numRows();
	   visibleCol = mf.numCols();
	   numTotal = visibleRow * visibleCol;
	   numMine = mf.numMines();
	   /*let the size of vField = the size of mf, initiated it with all COVERED*/
	   vField = new int[visibleRow][visibleCol];
	   for (int i = 0; i < visibleRow; i++) {
		   for (int j = 0; j < visibleCol; j++) {
			   vField[i][j] = COVERED;
		   }
	   }
      
   }
   
   
   /**
      Reset the object to its initial state (see constructor comments), using the same underlying MineField. 
   */     
   public void resetGameDisplay() {
	   /*set all values in vField to be COVERED*/
	   for (int i = 0; i < visibleRow; i++) {
		   for (int j = 0; j < visibleCol; j++) {
			   vField[i][j] = COVERED;
		   }
	   }
	   /*set the number of Guess and Uncovered squres = 0*/
	   numGuess = 0;
	   numUncovered = 0;
   }
  
   
   /**
      Returns a reference to the mineField that this VisibleField "covers"
      @return the minefield
    */
   public MineField getMineField() {
      /*return the mf as the reference*/
	  return mf;
   }
   
   
   /**
      get the visible status of the square indicated.
      @param row  row of the square
      @param col  col of the square
      @return the status of the square at location (row, col).  See the public constants at the beginning of the class
      for the possible values that may be returned, and their meanings.
      PRE: getMineField().inRange(row, col)
    */
   public int getStatus(int row, int col) {
	   /*return the number of adject mines if the square is uncovered*/
	   if(vField[row][col] == UNCOVERED) {
		   return mf.numAdjacentMines(row, col);
	   }
	   else {
		   /*status change when game is over only in 5 situations*/
		   if(isGameOver()) {
			   /*return INCORRECT_GUESS if the squre is MINE_GUESS and it has no mine*/
			   if(vField[row][col] == MINE_GUESS && mf.hasMine(row, col) == false) {
				   return INCORRECT_GUESS;
			   }
			   /*if the square is COVERED and it has mine*/			   
			   if(vField[row][col] == COVERED && mf.hasMine(row, col) == true) {
				   /*return MINE_GUESS if win*/ 
				   if(numUncovered == (numTotal - numMine)) {
					   return MINE_GUESS;
				   }
				   /*return MINE if lose*/
				   else {
					   return MINE;
				   }
			   }
			   /*if the square is QUESTION and it has mine*/
			   if(vField[row][col] == QUESTION && mf.hasMine(row, col) == true) {
				   /*return MINE_GUESS if win*/
				   if(numUncovered == (numTotal - numMine)) {
					   return MINE_GUESS;
				   }
				   /*return MINE if lose*/
				   else {
					   return MINE;
				   }
			   }
		   }
		   /*other situations just return whatever they are*/
		   return vField[row][col];
	   }
   }

   
   /**
      Return the the number of mines left to guess.  This has nothing to do with whether the mines guessed are correct
      or not.  Just gives the user an indication of how many more mines the user might want to guess.  So the value can
      be negative, if they have guessed more than the number of mines in the minefield.     
      @return the number of mines left to guess.
    */
   public int numMinesLeft() {
	   /*the number of mines left to guess = number of mine - number of Guess*/
	   return numMine - numGuess;
   }
 
   
   /**
      Cycles through covered states for a square, updating number of guesses as necessary.  Call on a COVERED square
      changes its status to MINE_GUESS; call on a MINE_GUESS square changes it to QUESTION;  call on a QUESTION square
      changes it to COVERED again; call on an uncovered square has no effect.  
      @param row  row of the square
      @param col  col of the square
      PRE: getMineField().inRange(row, col)
    */
   /*Right click the square*/
   public void cycleGuess(int row, int col) {
	   /*change to MINE_GUESS from COVERED*/
	   if(vField[row][col] == COVERED) {
		   vField[row][col] = MINE_GUESS;
		   /*the number of Guess plus 1*/
		   numGuess++;
	   }
	   /*change to QUESTION from MINE_GUESS*/
	   else if(vField[row][col] == MINE_GUESS) {
		   vField[row][col] = QUESTION;
		   /*the number of Guess minus 1*/
		   numGuess--;
	   }
	   /*chagne to COVERED from QUESTION*/
	   else if(vField[row][col] == QUESTION) {
		   vField[row][col] = COVERED;
	   }
	   else {
		   /*keep same if else situations*/
		   return;
	   }
      
   }

   
   /**
      Uncovers this square and returns false iff you uncover a mine here.
      If the square wasn't a mine or adjacent to a mine it also uncovers all the squares in 
      the neighboring area that are also not next to any mines, possibly uncovering a large region.
      Any mine-adjacent squares you reach will also be uncovered, and form 
      (possibly along with parts of the edge of the whole field) the boundary of this region.
      Does not uncover, or keep searching through, squares that have the status MINE_GUESS. 
      @param row  of the square
      @param col  of the square
      @return false   iff you uncover a mine at (row, col)
      PRE: getMineField().inRange(row, col)
    */
   public boolean uncover(int row, int col) {
	   /*run the inBound function to check if the location of a square is valid*/
	   if(mf.inRange(row, col) && vField[row][col] == COVERED) {
		   /*if the square has mine, change the status to EXPLODED_MINE and return false*/
		   if(mf.hasMine(row, col)) {
			   vField[row][col] = EXPLODED_MINE;
			   return false;
		   }
		   else {
			   /*if the square has no mine, uncover it*/
			   vField[row][col] = UNCOVERED;
			   /*the number of uncovered square plus 1*/
			   numUncovered++;
			   /*if there is no adjacent mines, recursively open up empty area as large as it can*/
			   if(mf.numAdjacentMines(row, col) == 0) {
				   /*8 possible surrounding locations*/
				   uncover(row - 1, col - 1);
				   uncover(row - 1, col);
				   uncover(row - 1, col + 1);
				   uncover(row, col - 1);
				   uncover(row, col + 1);
				   uncover(row + 1, col - 1);
				   uncover(row + 1, col);
				   uncover(row + 1, col + 1);
			   }
			   /*return true if there is mine next to it*/
			   return true;
		   } 
	   }
	   /*if the location of the square is invalid, return true*/
	   else {
		   return true;
	   }

   }
 
   
   /**
      Returns whether the game is over.
      @return whether game over
    */
   public boolean isGameOver() {
	  /*create a boolean variable gg*/
	  boolean gg = false;
	  /*check every square of vField*/
      for(int i = 0; i < this.visibleRow; i++) {
    	  for(int j = 0; j < this.visibleCol; j++) {
    		  /*return the game is over if any mine is uncovered*/
    		  if(vField[i][j] == EXPLODED_MINE) {
    			  gg = true;
    			  return gg;
    		  }
    	  }
      }
      /*return the game is over if all non-mine squares are open*/
      if(numUncovered == (numTotal - numMine)) {
    	  gg = true;
      }
      /*return the game is not over with no situations above*/
      return gg;
   }
 
   
   /**
      Return whether this square has been uncovered.  (i.e., is in any one of the uncovered states, 
      vs. any one of the covered states).
      @param row of the square
      @param col of the square
      @return whether the square is uncovered
      PRE: getMineField().inRange(row, col)
    */
   public boolean isUncovered(int row, int col) {
	   /*check if the square is uncovered*/
	   if(vField[row][col] == UNCOVERED) {
		   /*return true if it is*/
		   return true;
	   }
	   else {
		   /*return false if it is others*/
		   return false;
	   }
      
   }
   
 
   // <put private methods here>
   
}
