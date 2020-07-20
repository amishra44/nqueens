package com.aman.nqueens.algo;

/**
 * Implements N-Queens placement as per extended rule.
 * @author amanm
 */
public class NQueensExtended extends NQueensClassic {
	public NQueensExtended(int n) {
		super(n);
	}

	/**
	 * Verify placement of a N-Queens in a row and column (cell) as per the
	 * extended rule:<br>
	 * <ul>
	 *     <li>Classic rule is not violated.
	 *     <li>No three queens are on a straight line (at any angle).
	 * </ul>
	 * @param board Array representing the cells where queens are placed on the
	 *     board, with column number of placed queen as value in each array
	 *     element with row number as index.
	 * @param row Row number of given queen placement
	 * @param col Column number of given queen placement
	 * @return Valid flag (<code>true</code>/<code>false</code>)
	 */
	protected boolean valid(int[] board, int row, int col) {
		// Verify classic rule
		if(!super.valid(board, row, col)) {
			return false;
		}

		// Verify new rule (If there are 3 queens - queen1, queen2 and queen3,
		// then, slope of line between queen1 and queen2 should be same as
		// slope of line between queen1 and queen3)
		for(int i = 1; i <= row; i++) {
			for(int j = i + 1; j <= row; j++) {
				if(j * (col - board[row - i]) == i * (col - board[row - j])) {
					return false;
				}
			}
		}

		return true;
	}
}
