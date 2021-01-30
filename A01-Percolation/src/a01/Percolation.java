package a01;

import a01.Percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author Chad Zuniga and Kelsie Garcia
 *
 */
public class Percolation {
	private int size;
	private boolean[][] grid;
	private int vTop;
	private int vBot;
	private WeightedQuickUnionUF fullArray;
	private WeightedQuickUnionUF bwArray;
	private int count;
	
	/**
	 * This constructs the grid for percolation and unions the top row to the virtual top
	 * and the bottom row with the virtual bottom
	 * 
	 * @param n size of the grid
	 */
	public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Size of grid must be greater than 0.");
        }
		size = n;
		grid = new boolean[n][n];
		count = 0;
		
		fullArray = new WeightedQuickUnionUF(n * n + 2);
		bwArray = new WeightedQuickUnionUF(n * n + 2);
		vTop = n * n;
		vBot = n * n + 1;
		
		//connects the virtual top to the top row
		for(int i = 0; i < size; i++) {
			fullArray.union(vTop, i);
			bwArray.union(vTop, i);
		}
		
		//only unions virtual bottom with Back wash QU array
		int lastRowStart = size * size - size;
		for(int i = lastRowStart; i < size * size; i++) {
			bwArray.union(vBot, i);
		}
		
	}
	
	/**
	 * This method checks if the spot is opened and if the surrounding spots are
	 * open so they can be put in a union
	 * 
	 * @param i  Y axis value
	 * @param j  X axis value
	 */
	public void open(int i, int j) {
		if(!isOpen(i,j)) {
			this.grid[i][j] = true;
			count++;
		}
		
		//above
		if(i > 0 && isOpen(i - 1,j)) {
			fullArray.union( xyTo1d(i,j), xyTo1d(i - 1,j) );
			bwArray.union( xyTo1d(i,j), xyTo1d(i - 1,j) );
		}
		
		//right
		if(j < size - 1 && isOpen(i,j + 1)) {
			fullArray.union( xyTo1d(i,j), xyTo1d(i,j + 1) );
			bwArray.union( xyTo1d(i,j), xyTo1d(i,j + 1) );

		}
		
		//bottom
		if(i < size - 1 && isOpen(i + 1,j)) {
			fullArray.union( xyTo1d(i,j), xyTo1d(i + 1,j) );
			bwArray.union( xyTo1d(i,j), xyTo1d(i + 1,j) );

		}
		
		//left
		if(j > 0 && isOpen(i,j - 1)) {
			fullArray.union( xyTo1d(i,j), xyTo1d(i,j - 1) );
			bwArray.union( xyTo1d(i,j), xyTo1d(i,j - 1) );
		}
	}
	
	/**
	 * This method returns the amount of spots opened in the grid
	 * 
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * This method checks to see if the spot is open in the grid.
	 * 
	 * @return if the spot is open
	 */
	public boolean isOpen(int i, int j) {
		return this.grid[i][j];
	}
	
	/**
	 * This method returns if the opened spots are connected with the top and are full
	 * and this method does not reach the bottom to avoid back wash.
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int i, int j) {
		return fullArray.connected(vTop,xyTo1d(i,j));
	}
	
	/**
	 * This method checks if the virtual top is connected with the virtual bottom
	 * 
	 * @return
	 */
	public boolean percolates() {
		return bwArray.connected(vBot, vTop);
	}
	
	private int xyTo1d(int i, int j) {
		return (size * i) + j;
	}
	
	//Testing purposes
	public static void showGrid(Percolation p) {
		for(int i = 0; i <p.size; i++) {
			for(int j = 0; j < p.size; j++) {
				System.out.print("|");
				if(p.grid[i][j] == false) {
					System.out.print("x");
				} else {
					System.out.print("o");
				}
			}
			System.out.print("|\n");
		}
		System.out.println();
	}
}

