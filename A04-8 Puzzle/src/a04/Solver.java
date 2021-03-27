package a04;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;
import edu.princeton.cs.algs4.StdOut;

/**
 * This class is an immutable data type that solves an 8-puzzle
 * using the A* algorithm to find the winning board in the 
 * least amount of moves.
 * @author Kelsie Garcia and Chad Zuniga
 *
 */
public class Solver {
	private SearchNode solved = null;
	
    private class SearchNode implements Comparable<SearchNode>{             
        private int moves;                
        private Board board;
        private SearchNode prev;
        private int priority;


        public SearchNode(Board b, SearchNode prev, int moves) {
            this.moves = moves;
            board = b;
            this.prev = prev;
            priority = b.hamming() + moves;
        }


		@Override
		public int compareTo(SearchNode o) {
			if(this.priority > o.priority) {
				return 1;
			}else if(this.priority < o.priority){
				return -1;
			}else {
				return 0;
			}
		}
    }
	
	// find a solution to the initial board (using the A* algorithm)
	public Solver(Board initial) {
		if(!initial.isSolvable()) throw new IllegalArgumentException("Board is not solvable.");
		int boardSize = initial.size() * initial.size();
		MinPQ<SearchNode> pq = new MinPQ<>();
		pq.insert(new SearchNode(initial,null,0));
		
		
//		Integer[] arr = new Integer[boardSize];
//		for(int i = 0; i < boardSize - 1; i++) {
//			arr[i] = i + 1;
//		}
//		arr[boardSize - 1] = 0;
//
//		Board goalBoard = new Board(arr);
		
		SearchNode initialNode = pq.delMin();
		while(!initialNode.board.isGoal()) {
			Iterable<Board> neighbors = initialNode.board.neighbors();

			while(neighbors.iterator().hasNext()) {
				Board temp = neighbors.iterator().next();
				pq.insert(new SearchNode(temp, initialNode, initialNode.moves + 1));
			}
			initialNode = pq.delMin();
		}
		if(initialNode.board.isGoal()) {
			solved = initialNode;
		}
	}
	
	// min number of moves to solve initial board
	public int moves() {
		return solved.moves;
	}
	
	// sequence of boards in a shortest solution
	public Iterable<Board> solution(){
		Stack<Board> s = new Stack<Board>();
		for(SearchNode i = solved; i != null; i = i.prev) {
			s.push(i.board);
		}
		return s;
	}
	
	public static void main(String[] args) {
		//Integer[] testArr = {1,2,3,4,5,6,7,8,0};
		Integer[] testArr = {0,1,3,4,2,5,7,8,6};
//		Board b = new Board(testArr);
//		
//		Solver s = new Solver(b);
//		System.out.println(s.moves());
	
	    // create initial board from file
//	    In in = new In(args[0]);
//	    int N = in.readInt();
//	    int[][] blocks = new int[N][N];
//	    for (int i = 0; i < N; i++)
//	        for (int j = 0; j < N; j++)
//	            blocks[i][j] = in.readInt();
	    Board initial = new Board(testArr);

	    // check if puzzle is solvable; if so, solve it and output solution
	    if (initial.isSolvable()) {
	        Solver solver = new Solver(initial);
	        StdOut.println("Minimum number of moves = " + solver.moves());
	        for (Board board : solver.solution())
	            StdOut.println(board);
	    }

	    // if not, report unsolvable
	    else {
	        StdOut.println("Unsolvable puzzle");
	    }
	}
	
}
