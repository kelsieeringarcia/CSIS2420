package a04;

import java.util.Arrays;

import edu.princeton.cs.algs4.Stack;

/**
 * This class is for the board for the 8 puzzle. Constructing the board and
 * checking information of the current puzzle state.
 * 
 * @author Kelsie Garcia and Chad Zuniga
 *
 */
public class Board {
	private int N;
	private Integer[] board;
	private int blank;

	/**
	 * Constructs a board from an N-by-N array of blocks (where blocks[i][j] = block
	 * in row i, column j)
	 * 
	 * @param blocks
	 */
	public Board(int[][] blocks) {
		this.N = blocks.length;
		this.board = new Integer[N * N];
		int index = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				this.board[index] = blocks[i][j];
				if (blocks[i][j] == 0) {
					this.blank = index;
				}
				index++;
			}
		}
	}

	/**
	 * This is a constructor that takes a 1d array.
	 * 
	 * @param blocks
	 */
	public Board(Integer[] blocks) {
		this.N = (int) Math.sqrt(blocks.length);
		this.board = blocks;
		this.blank = Arrays.asList(board).indexOf(0);
	}

	/**
	 * This method returns the size of the board.
	 * 
	 * @return The board size N
	 */
	public int size() {
		return N;
	}

	/**
	 * This method goes through the board array to verify how many blocks are out of
	 * place
	 * 
	 * @return number of blocks out of place
	 */
	public int hamming() {
		int count = 0;
		for (int i = 0; i < N * N; i++) {
			if (board[i] != 0 && board[i] != i + 1) {
				count++;
			}
		}
		return count;
	}

	/**
	 * This method adds up the amount of moves it will take to get in final
	 * position.
	 * 
	 * @return sum of Manhattan distances between blocks and goal
	 */
	public int manhattan() {
		int result = 0;

		for (int i = 0; i < (N * N); i++) {
			if (board[i] == 0)
				continue;
//			System.out.println(String.format("i:%d col:%d row:%d", i+1, i%N, i/N));
			double rowDifference = Math
					.abs(Math.ceil((double) board[i] / (double) N) - Math.ceil((double) (i + 1) / (double) N));
			int colDifference = Math.abs((board[i] % N == 0 ? N : board[i] % N) - ((i + 1) % N == 0 ? N : (i + 1) % N));
			result += (int) rowDifference + colDifference;
		}

		return result;
	}

	/**
	 * This method checks the board array to see if it is complete or not
	 * 
	 * @return is this board the goal board?
	 */
	public boolean isGoal() {
		for (int i = 0; i < N * N - 1; i++) {
			if (board[i] != i + 1) {
				return false;
			}
		}
		return true;
	}

	/**
	 * This method checks to see if the square is solvable this checks for even or
	 * odd sized square.
	 * 
	 * @return if the puzzle can be solved
	 */
	public boolean isSolvable() {
		return N % 2 == 0 ? (getInvCount() + getBlankRow()) % 2 != 0 : getInvCount() % 2 == 0;
	}

	// Checks for inversions
	private int getInvCount() {
		int inv_count = 0;
		for (int i = 0; i < N * N - 1; i++) {
			for (int j = i + 1; j < N * N; j++) {
				if (board[j] > 0 && board[i] > 0 && board[i] > board[j])
					inv_count++;
			}
		}
		return inv_count;
	}

	/**
	 * This method looks for the column of the blank square.
	 * 
	 * @return
	 */
	public int getBlankCol() {
		int res = Arrays.asList(board).indexOf(0) % N;
		return res;
	}

	/**
	 * This method looks for the row of the blank square.
	 * 
	 * @return
	 */
	public int getBlankRow() {

		double top = Math.ceil((double) Arrays.asList(board).indexOf(0));
		double bot = (double) N;
		double res = top / bot;
		return (int) res;
	}

	/**
	 * This method compares two boards to each other.
	 */
	public boolean equals(Object y) {
		if (y == this)
			return true;
		if (y == null)
			return false;

		if (y.getClass() != this.getClass())
			return false;

		Board that = (Board) y;

		return Arrays.equals(this.board, that.board);
	}

	/**
	 * This method checks the neighboring tiles of each tile and keeps track of the
	 * empty block.
	 * 
	 * @return
	 */
	public Iterable<Board> neighbors() {
		Stack<Board> neighbors = new Stack<>();

		// row above
		if (getBlankRow() - 1 >= 0 && N > 1) {
			Integer[] newBoard = board.clone();
			int temp = newBoard[blank];
			newBoard[blank] = newBoard[blank - N];
			newBoard[blank - N] = temp;
			neighbors.push(new Board(newBoard));
		}

		// row below
		if (getBlankRow() + 1 < N && N > 1) {
			Integer[] newBoard = board.clone();
			int temp = newBoard[blank];
			newBoard[blank] = newBoard[blank + N];
			newBoard[blank + N] = temp;
			neighbors.push(new Board(newBoard));
		}

		// col left
		if (getBlankCol() - 1 >= 0 && N > 1) {
			Integer[] newBoard = board.clone();
			int temp = newBoard[blank];
			newBoard[blank] = newBoard[blank - 1];
			newBoard[blank - 1] = temp;
			neighbors.push(new Board(newBoard));
		}

		// col right
		if (getBlankCol() + 1 < N && N > 1) {
			Integer[] newBoard = board.clone();
			int temp = newBoard[blank];
			newBoard[blank] = newBoard[blank + 1];
			newBoard[blank + 1] = temp;
			neighbors.push(new Board(newBoard));
		}

		return neighbors;
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append(String.format("%d%n", N));
		for (int i = 0; i < N * N; i++) {
			s.append(String.format("%2d ", board[i]));

			if ((i + 1) % N == 0) {
				s.append("\n");
			}
		}
		return s.toString();
	}

	/**
	 * TESTING CLIENT
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] blocks = { { 1, 2, 3 }, { 4, 5, 0 }, { 7, 8, 6 } };
//		int[][] blocks = {
//				{8,1,3},
//				{4,0,2},
//				{7,6,5}
//		};
//		int[][] blocks = {
//				{1,2,3},
//				{0,7,6},
//				{4,5,8}
//		};
//		int[][] blocks = {
//				{1,2,3},
//				{0,4,6},
//				{8,5,7}
//		};
//		int[][] blocks = {
//				{1,2,3,4},
//				{5,6,0,8},
//				{9,10,7,11},
//				{13,14,15,12}
//		};

		Board b = new Board(blocks);
		System.out.println(b.isSolvable());
//		System.out.println(b);
//		Iterable<Board> test = b.neighbors();
//		for(Board el : test) {
//			System.out.println("++++++++++++++++");
//			System.out.println(b);
//			System.out.println(el);
//			System.out.println("++++++++++++++++");
//		}
	}
}
