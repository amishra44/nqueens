package com.aman.nqueens.algo;

/**
 * Implements N-Queens placement as per classic rule.
 * @author amanm
 */
public class NQueensClassic extends AbstractNQueens {
	public NQueensClassic(int n) {
		super(n);
	}

	/**
	 * Verify placement of a N-Queens in a row and column (cell) as per the
	 * classic rule:<br>
	 * <ul>
	 *     <li>No two queens intersect vertically, horizontally and diagonally.
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
		for(int i = 1; i <= row; i++) {
			if(board[row - i] == col
					|| board[row - i] == (col - i)
					|| board[row - i] == (col + i)) {
				return false;
			}
		}

		return true;
	}
}
