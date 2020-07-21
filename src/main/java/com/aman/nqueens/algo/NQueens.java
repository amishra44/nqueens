package com.aman.nqueens.algo;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Interface to find valid placement of N-Queens.
 * @author amanm
 */
public interface NQueens {
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
	public int[] place(int startCol, int incrementCol, AtomicBoolean done);
}