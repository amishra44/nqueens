package com.aman.nqueens.runner;

import com.aman.nqueens.algo.NQueens;
import com.aman.nqueens.algo.NQueensClassic;
import com.aman.nqueens.algo.NQueensExtended;
import com.aman.nqueens.algo.NQueensSolver;
import com.aman.nqueens.algo.NQueensThreadedSolver;

/**
 * Main program to solve the N-Queens problem. Solves two types of N-Queens
 * problem:<br>
 * <ul>
 *     <li> Classic N-Queens problem - No two queens intersect vertically,
 *          horizontally and diagonally.
 *     <li> Extended N-Queens problem - Respects classic rule. Additionally,
 *          no three queens are on a straight line (at any angle).
 * </ul>
 * Solves the extended problem as default. User can request to solve the
 * classic problem using a command line flag.<br><br>
 * It can use multiple threads to navigate the search space faster. Currently,
 * all concurrent searches are rooted at a cell on the first row. Uses a single
 * thread as default. User can provides the number of threads using a command
 * line argument.
 * @author amanm
 */
public class NQueensRunner {
	/**
	 * Main method that is invoked by JVM.
	 * @param args Command line arguments
	 */
	public static void main(String[] args) {
		NQueensParameters params = NQueensParameters.parse(args);
		
		// Solve classic or extended problem
		if(params.isClassic()) {
			solveClassic(params.getSize(), params.getNumThreads());
		} else {
			solveExtended(params.getSize(), params.getNumThreads());
		}
	}

	private static void solveHelper(int size, int numThreads, NQueensSolver nQueensSolver, String type) {
		long start = System.currentTimeMillis();
		int[] result = nQueensSolver.solve();
		long end = System.currentTimeMillis();
		
		System.out.println(type + " Solution:");
		
		if(result == null) {
			System.out.println("There is no solution for board of size: " + size);
			return;
		}

		System.out.println("Size: " + size);
		System.out.println("Number of threads: " + numThreads);
		printBoard(size, result);
		System.out.println("Took " + (end - start) + " ms");
	}
	
	private static void solveClassic(int size, int numThreads) {
		NQueens nQueens = new NQueensClassic(size);
		NQueensSolver nQueensSolver = new NQueensThreadedSolver(size, numThreads, nQueens);
		
		solveHelper(size, numThreads, nQueensSolver, "Classic");
	}

	private static void solveExtended(int size, int numThreads) {
		NQueens nQueens = new NQueensExtended(size);
		NQueensSolver nQueensSolver = new NQueensThreadedSolver(size, numThreads, nQueens);
		
		solveHelper(size, numThreads, nQueensSolver, "Extended");
	}
	
	private static void printBoard(int n, int[] result) {
		for(int i = 0; i < n; i++) {
			StringBuilder builder = new StringBuilder();
			for(int j = 0; j < n; j++) {
				if(result[i] == j) {
					builder.append(((j == 0) ? "": " ") + "Q");
				} else {
					builder.append(((j == 0) ? "": " ") + ".");
				}
			}
			System.out.println(builder);
		}
	}
}