package a01;

import a01.Percolation;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * @author chad.zuniga
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
	 * 
	 * @param n
	 */
	public Percolation(int n) {
		size = n;
		grid = new boolean[n][n];
		count = 0;
		
		fullArray = new WeightedQuickUnionUF(n*n+2);
		bwArray = new WeightedQuickUnionUF(n*n+2);
		vTop = n*n;
		vBot = n*n + 1;
		
		for(int i = 0; i < size; i++) {
			fullArray.union(vTop, i);
			bwArray.union(vTop, i);
		}
		
		//only unions virtual bottom with Backwash QU array
		int lastRowStart = size*size - size;
		for(int i = lastRowStart; i < size*size; i++) {
			bwArray.union(vBot, i);
		}
		
	}
	
	/**
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
		if(i > 0 && isOpen(i-1,j)) {
			fullArray.union( xyTo1d(i,j), xyTo1d(i-1,j) );
			bwArray.union( xyTo1d(i,j), xyTo1d(i-1,j) );
		}
		
		//right
		if(j < size-1 && isOpen(i,j+1)) {
			fullArray.union( xyTo1d(i,j), xyTo1d(i,j+1) );
			bwArray.union( xyTo1d(i,j), xyTo1d(i,j+1) );

		}
		
		//bottom
		if(i < size-1 && isOpen(i+1,j)) {
			fullArray.union( xyTo1d(i,j), xyTo1d(i+1,j) );
			bwArray.union( xyTo1d(i,j), xyTo1d(i+1,j) );

		}
		
		//left
		if(j > 0 && isOpen(i,j-1)) {
			fullArray.union( xyTo1d(i,j), xyTo1d(i,j-1) );
			bwArray.union( xyTo1d(i,j), xyTo1d(i,j-1) );
		}
	}
	
	/**
	 * @return the count
	 */
	public int getCount() {
		return count;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isOpen(int i, int j) {
		return this.grid[i][j];
	}
	
	/**
	 * 
	 * @param i
	 * @param j
	 * @return
	 */
	public boolean isFull(int i, int j) {
		return fullArray.connected(vTop,xyTo1d(i,j));
	}
	
	/**
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

