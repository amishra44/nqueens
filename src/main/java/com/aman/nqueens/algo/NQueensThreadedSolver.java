package com.aman.nqueens.algo;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * Implements N-Queens solver using given <code>NQueens</code> implementation
 * and given number of threads. If number of threads is greater than 1, uses
 * multiple threads to solve the problem concurrently. 
 * @author amanm
 */
public class NQueensThreadedSolver implements NQueensSolver {
	protected int size;
	protected int numThreads;
	protected NQueens nQueens;

	protected boolean solved;
	protected int[] board;
	protected AtomicBoolean done; // Shared flag
	
	public NQueensThreadedSolver(int size, int numThreads, NQueens nQueens) {
		this.size = size;
		this.numThreads = numThreads;
		this.nQueens = nQueens;
	
		done = new AtomicBoolean();
	}
	
	/**
	 * Solves the N-Queens problem and finds a valid placement of queens. 
	 * @return Array representing the cells where queens are placed on the
	 * board, with column number of placed queen as value in each array
	 * element with row number as index.
	 */
	public int[] solve() {
		if(solved) {
			return board;
		}

		// Create threads
		SolverThread[] threads = new SolverThread[numThreads];
		for(int i = 0; i < numThreads; i++) {
			threads[i] = new SolverThread(size, numThreads, i, done, nQueens);
		}
		
		// Start threads
		for(int i = 0; i < numThreads; i++) {
			threads[i].start();
		}
		
		// Wait for completion
		for(int i = 0; i < numThreads; i++) {
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				System.out.println("Error in thread execution: " + e);
			}
		}
		
		// Read the solution
		for(int i = 0; i < numThreads; i++) {
			if(threads[i].board != null) {
				board = threads[i].board;
				break;
			}
		}

		solved = true;
		return board;
	}
	
	/**
	 * Thread to solve N-Queens placement concurrently.
	 * @author amanm
	 *
	 */
	public static class SolverThread extends Thread {
		int n;
		int numThreads;
		int id;

		NQueens nQueens;
		AtomicBoolean done; // Shared flag
		int[] board;

		SolverThread(int n, int numThreads, int id, AtomicBoolean done, NQueens nQueens) {
			this.n = n;
			this.numThreads = numThreads;
			this.id = id;
			this.nQueens = nQueens;
			
			this.done = done;
		}

		public void run() {
			// Find placement starting at given row, with given number of
			// threads and shared flag.
			board = nQueens.place(id, numThreads, done);
		}
	}
}
