package com.aman.nqueens.runner;

public class NQueensParameters {
	private String[] args;
	private int size;
	private int numThreads;
	private boolean isClassic;

	private NQueensParameters() {
		size = 0;
		numThreads = 1;
		isClassic = false;
	}
	
	public String[] getArgs() {
		return args;
	}

	public int getSize() {
		return size;
	}

	public int getNumThreads() {
		return numThreads;
	}
	
	public boolean isClassic() {
		return isClassic;
	}
	
	public static NQueensParameters parse(String[] args) {
		NQueensParameters params = new NQueensParameters();
		params.args = args;
		
		if(args.length < 1) {
			printHelp();
		}
		
		for(int i = 0; i < args.length; i++) {
			switch(args[i]) {
			case "-help":
				printHelp();
				break;
			case "-threads":
				if(i + 1 >= args.length) {
					System.out.println("-threads: Number of threads is required");
					printHelp();
				}
				try {
					params.numThreads = Integer.parseInt(args[i + 1]);
					if(params.numThreads < 1) {
						params.numThreads = 1;
					}
				} catch(Exception e) {
					System.out.println("-threads: Invalid number of threads: " + args[i]);
					printHelp();
				}
				i++;
				break;
			case "-classic":
				params.isClassic = true;
				break;
			default:
				if(i == args.length - 1) {
					try {
						params.size = Integer.parseInt(args[i]);
					} catch(Exception e) {
						System.out.println("Invalid board size: " + args[i]);
						printHelp();
					}
				} else {
					System.out.println(args[i] + ": Invalid option");
					printHelp();
				}
			}
		}
		
		if(params.size <= 0) {
			System.out.println("Invalid board size: " + params.size);
			printHelp();
		}
		
		return params;
	}

	private static void printHelp() {
		System.out.println("Usage: java NQueensSolver [options] <board-size>");
		System.out.println("Options:");
		System.out.println("    -help                       print this help and exit");
		System.out.println("    -threads <num-threads>      number of threads (for concurrent algorithm)");
		System.out.println("    -classic                    solve classic N-Queens");
		System.exit(0);
	}
}
