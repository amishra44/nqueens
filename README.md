# Introduction

Program to solve the N-Queens problem for a given board size. Solves two variants of N-Queens problem:

* Classic N-Queens problem - No two queens intersect vertically, horizontally, and diagonally. 
* Extended N-Queens problem - Respects classic rule. Additionally, no three queens are on a straight line (at any angle). 

Solves the extended problem as default. User can request to solve the classic problem using a command line flag.

It can use multiple threads to navigate the search space faster. Uses a single thread as default. User can provide the number of threads using a command line argument. Currently, all concurrent searches are rooted at a cell on the first row.

This implementation currently solves N-Queens problem for board size in range 1 to 30 (both included), in a reasonable amount of time (less than 60 seconds) and space.

# Design and Implementation

## Design

The design consists of following layers:

* Runner - Main program that can be executed by user. Problem type, board size and number of threads can be passed as command line arguments.
* Solver - Solves the N-Queens problem using given number of threads and a placement strategy.
* Placement Strategy - Finds a valid placement for a problem type.

## Implementation

The implementation consists of following interfaces and classes:

* NQueens - Interface to find valid placement of N-Queens.
* AbstractNQueens - An implementation of `NQueens` interface.
* NQueensClassic - Implements N-Queens placement as per classic rule.
* NQueensExtended - Implements N-Queens placement as per extended rule.
* NQueensSolver - Interface to solve N-Queens problem.
* NQueensThreadedSolver - Implements N-Queens solver using given `NQueens` implementation and given number of threads. 
* NQueensParameters - Parses and stores command line arguments for N-Queens program.
* NQueensRunner - Main program to solve the N-Queens problem.

# Installation

## Pre-requisites

Following are the pre-requisites for running N-Queens program:

* JDK 8 or higher
* Gradle 6.5.1 or higher
* Git 2.26.0 or higher

## Checkout Code and Build

Checkout code using following command:

`git clone https://github.com/amishra44/nqueens.git`

Build code using following command:

`gradle clean build`

Build a jar using following command:

`gradle clean jar`

# Running

Run the N-Queens program using following commands:

	gradle run --args="[options] <board-size>"
	Options:
	    -help                       print this help and exit
	    -threads <num-threads>      number of threads (for concurrent algorithm)
	    -classic                    solve classic N-Queens

Alternatively, you can directly run the executable jar:

	java -jar build/libs/nqueens.jar [options] <board-size>
	Options:
	    -help                       print this help and exit
	    -threads <num-threads>      number of threads (for concurrent algorithm)
	    -classic                    solve classic N-Queens

## Examples

Some examples are given below:

Solve extended N-Queens problem for board size 8:

	gradle run --args="8"
	Extended Solution:
	Size: 8
	Number of threads: 1
	. . Q . . . . .
	. . . . Q . . .
	. . . . . . . Q
	. . . Q . . . .
	Q . . . . . . .
	. . . . . . Q .
	. Q . . . . . .
	. . . . . Q . .
	Took 3 ms

Solve classic N-Queens problem for board size 8:

	java -jar build/libs/nqueens.jar -classic 8
	Classic Solution:
	Size: 8
	Number of threads: 1
	Q . . . . . . .
	. . . . Q . . .
	. . . . . . . Q
	. . . . . Q . .
	. . Q . . . . .
	. . . . . . Q .
	. Q . . . . . .
	. . . Q . . . .
	Took 2 ms

Solve extended N-Queens problem for board size 8, using 2 threads:

	gradle run --args="-threads 2 8"
	Extended Solution:
	Size: 8
	Number of threads: 2
	. . . Q . . . .
	. Q . . . . . .
	. . . . . . . Q
	. . . . Q . . .
	. . . . . . Q .
	Q . . . . . . .
	. . Q . . . . .
	. . . . . Q . .
	Took 3 ms

# Future

Some future enhancements are listed below:
* Use branch-and-bound technique to prune the search space.
* Use concurrent searches at deeper levels.
