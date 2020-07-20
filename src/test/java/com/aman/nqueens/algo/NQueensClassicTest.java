package com.aman.nqueens.algo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;

import java.util.concurrent.atomic.AtomicBoolean;

public class NQueensClassicTest {
	@Test
	void testValid_Valid_FirstRow() {
		AbstractNQueens nQueens = new NQueensClassic(4);
		int[] board = new int[] {0, 0, 0, 0};
		
		boolean result = nQueens.valid(board, 0, 0);
		assertTrue(result, "Placement is valid");
	}

	@Test
	void testValid_Valid_SecondRow() {
		AbstractNQueens nQueens = new NQueensClassic(4);
		int[] board = new int[] {0, 0, 0, 0};
		
		boolean result = nQueens.valid(board, 1, 2);
		assertTrue(result, "Placement is valid");
	}

	@Test
	void testValid_Valid_MiddleRow() {
		AbstractNQueens nQueens = new NQueensClassic(4);
		int[] board = new int[] {1, 3, 0, 0};
		
		boolean result = nQueens.valid(board, 2, 0);
		assertTrue(result, "Placement is valid");
	}

	@Test
	void testValid_Valid_LastRow() {
		AbstractNQueens nQueens = new NQueensClassic(4);
		int[] board = new int[] {1, 3, 0, 0};
		
		boolean result = nQueens.valid(board, 3, 2);
		assertTrue(result, "Placement is valid");
	}

	@Test
	void testValid_Invalid_SecondRow_Diagonal() {
		AbstractNQueens nQueens = new NQueensClassic(4);
		int[] board = new int[] {0, 0, 0, 0};
		
		boolean result = nQueens.valid(board, 1, 1);
		assertFalse(result, "Placement is invalid");
	}

	@Test
	void testValid_Invalid_MiddleRow_Diagonal() {
		AbstractNQueens nQueens = new NQueensClassic(4);
		int[] board = new int[] {1, 3, 0, 0};
		
		boolean result = nQueens.valid(board, 2, 2);
		assertFalse(result, "Placement is valid");
	}

	@Test
	void testValid_Invalid_LastRow_Vertical() {
		AbstractNQueens nQueens = new NQueensClassic(4);
		int[] board = new int[] {1, 3, 0, 0};
		
		boolean result = nQueens.valid(board, 3, 3);
		assertFalse(result, "Placement is valid");
	}
	
	@Test
	void testPlace_NoValidPlacement() {
		AbstractNQueens nQueens = new NQueensClassic(3);
		int[] board = nQueens.place(0, 1, new AtomicBoolean());
		
		int[] expectedBoard = null;
		assertArrayEquals(expectedBoard, board, "Board");
	}

	@Test
	void testPlace_ValidPlacement_FirstCol() {
		AbstractNQueens nQueens = new NQueensClassic(4);
		int[] board = nQueens.place(0, 1, new AtomicBoolean());
		
		int[] expectedBoard = new int[] {1, 3, 0, 2};
		assertArrayEquals(expectedBoard, board, "Board");
	}

	@Test
	void testPlace_ValidPlacement_SecondCol() {
		AbstractNQueens nQueens = new NQueensClassic(4);
		int[] board = nQueens.place(1, 2, new AtomicBoolean());
		
		int[] expectedBoard = new int[] {1, 3, 0, 2};
		assertArrayEquals(expectedBoard, board, "Board");
	}
}