package a01;

import java.util.Random;

import edu.princeton.cs.algs4.StdStats;

/**
 * @author Chad Zuniga and Kelsie Garcia
 *
 */
public class PercolationStats {
	Random rand = new Random();
	private double[] stats;

	/**
	 * This constructor simulates percolation at a given amount when the spots are opened
	 * at random. The results are stored in a array to be used to give statistics on the 
	 * simulation.
	 *  
	 * @param N size of the grid
	 * @param T number of trials in the simulation
	 */
	public PercolationStats(int N, int T) {
        if (N <= 0) {
            throw new IllegalArgumentException("Size of grid must be greater than 0.");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("There must be more than 0 trails.");
        }
		stats = new double[T];
		//iterates through all the trials to add the results of each percolation to the stats array
		for(int i = 0; i < T; i++) {
			Percolation p = new Percolation(N);
			while(!p.percolates()) {
				p.open(rand.nextInt(N),rand.nextInt(N));
			}
			//number of slots opened * double(changing int to double) / size^2
			stats[i] = p.getCount() * 1.0 / (N * N);
		}
    }

	/**
	 * This method will take the stats from the simulated percolation and get the mean 
	 * 
	 * @return
	 */
    public double mean() {
        return StdStats.mean(stats);
    }

    /**
     * This method will take the stats from the simulated percolation and get the standard deviation
     * 
     * @return
     */
    public double stddev() {
        return StdStats.stddev(stats);
    }

    /**
     * This method will take the stats from the simulated percolation and get the confidence low
     * at 95% end point
     * 
     * @return
     */
    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(stats.length);
    }

    /**
     * This method will take the stats from the simulated percolation and get the confidence high
     * at 95% end point
     * 
     * @return
     */
    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(stats.length);
    }

}



