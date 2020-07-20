package com.aman.nqueens.algo;

/**
 * Interface to solve N-Queens problem.
 * @author amanm
 */
public interface NQueensSolver {
	/**
	 * Solves the N-Queens problem and finds a valid placement of queens. 
	 * @return Array representing the cells where queens are placed on the
	 * board, with column number of placed queen as value in each array
	 * element with row number as index.
	 */
	public int[] solve();
}