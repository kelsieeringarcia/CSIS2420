package a04;

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

        /**
         * This constructs a search node with a current board, previous board, and 
         * the current moves used to get to that board.
         * @param b
         * @param prev
         * @param moves
         */
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
	
	/**
	 * This method is used to find a solution to the initial board 
	 * (using the A* algorithm)
	 * 
	 * @param initial
	 */
    public Solver(Board initial) {
        if(!initial.isSolvable()) throw new IllegalArgumentException("Board is not solvable.");
        MinPQ<SearchNode> pq = new MinPQ<>();
        pq.insert(new SearchNode(initial,null,0));
        
        SearchNode initialNode = pq.delMin();
        while(!initialNode.board.isGoal()) {
            Iterable<Board> neighbors = initialNode.board.neighbors();

            for(Board el : neighbors) {
                pq.insert(new SearchNode(el, initialNode, initialNode.moves + 1));
            }

            initialNode = pq.delMin();
        }
        if(initialNode.board.isGoal()) {
            solved = initialNode;
        }
    }
	
	/**
	 * A minimum number of moves to solve initial board
	 * @return the number of moves
	 */
	public int moves() {
		if(!solved.board.isSolvable()) {
			return -1;
		}
		return solved.moves;
	}
	
	/**
	 * A sequence of boards in a shortest solution
	 * @return 
	 */
	public Iterable<Board> solution(){
		Stack<Board> s = new Stack<Board>();
		for(SearchNode i = solved; i != null; i = i.prev) {
			s.push(i.board);
		}
		return s;
	}
	
	/**
	 * TEST CLIENT
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
//		Integer[] testArr = {1,2,3,4,5,6,8,7,0};
//		Integer[] testArr = {0,1,3,4,2,5,7,8,6};
//		int[][] testArr = { { 1, 2, 3, 4 }, { 5, 6, 0, 8 }, { 9, 10, 7, 11 }, { 13, 14, 15, 12 } };
		Integer[] testArr = {1,0,3,2};
	
	
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
