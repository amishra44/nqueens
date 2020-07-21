package com.aman.nqueens.algo;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * An implementation of <code>NQueens</code> interface. Implements the place()
 * method of <code>NQueens</code> and provides an abstract method to verify a
 * placement of queen in a row and column (cell).
 * @author amanm
 */
public abstract class AbstractNQueens implements NQueens {
	protected int n;
	
	public AbstractNQueens(int n) {
		this.n = n;
	}
	
	/**
	 * Find a valid placement of queens, starting from first row and given
	 * column. If a search is unsuccessful, start search from first row and
	 * next column at a given distance from the column in first row where last
	 * search was started. 
	 * @param startCol Start column in first row
	 * @param incrementCol Distance of next column to start search 
	 * @param done Flag to track if a solution has been found.
	 * @return Array representing the cells where queens are placed on the
	 * board, with column number of placed queen as value in each array
	 * element with row number as index.
	 */
	public int[] place(int startCol, int incrementCol, AtomicBoolean done) {
		int[] board = new int[n];

		int numQueens = 0; // No queens placed
		
		// Start with first row and given column
		int currRow = 0;
		int currCol = startCol;

		while(numQueens < n && !done.get()) {
			boolean foundCol = false;
			
			// For current row, check columns incrementally for a valid
			// placement
			while(currCol < n) {
				// Is placement in current cell valid? 
				if(valid(board, currRow, currCol)) {
					board[currRow] = currCol;
					
					// Number of queens that have been placed
					numQueens++;
				
					currRow++;
					currCol = 0;
					foundCol = true;
					break;
				}
				
				// Move to next column
				if(currRow == 0) {
					currCol += incrementCol;
				} else {
					currCol += 1;
				}
			}
			
			// If no valid placement is found in any column of
			// current row, we need to BACKTRACK.
			if(!foundCol) {
				currCol = -1;
				
				// Move up the rows, until a row with some unexplored columns
				// is found
				while(--currRow >= 0) {
					// Update number of placed queens 
					numQueens--;
					
					if(currRow == 0) {
						// For first row explore only half of the columns
						// because of symmetry.
						if(board[currRow] + incrementCol < (n + 1) / 2) {
							currCol = board[currRow] + incrementCol;
							break;
						}
					} else {
						if(board[currRow] + 1 < n) {
							currCol = board[currRow] + 1;
							break;
						}
					}
				}

				// If no unexplored columns are left in rows that have already
				// been explored.
				if(currCol == -1) {
					break;
				}
			}
		}
		
		// If all queens have been placed, this is a valid placement
		if(numQueens == n) {
			done.set(true);
		} else {
			board = null;
		}

		return board;
	}
	
	/**
	 * Abstract method to verify a placement of queen in a row and column
	 * (cell). Classes that extend this class should provide an implementation
	 * of this method as per the respective rules.
	 * @param board Array representing the cells where queens are placed on the
	 *     board, with column number of placed queen as value in each array
	 *     element with row number as index.
	 * @param row Row number of given queen placement
	 * @param col Column number of given queen placement
	 * @return Valid flag (<code>true</code>/<code>false</code>)
	 */
	protected abstract boolean valid(int[] board, int row, int col);
}