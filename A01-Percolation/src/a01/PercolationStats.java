package a01;

import java.util.Random;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * @author chad.zuniga
 *
 */
public class PercolationStats {
	Random rand = new Random();
	private double[] stats;

	public PercolationStats(int N, int T) {
        if (N <= 0) {
            throw new IllegalArgumentException("Size of grid must be greater than 0.");
        }
        if (T <= 0) {
            throw new IllegalArgumentException("There must be more than 0 trails.");
        }
		stats = new double[T];
		for(int i = 0; i < T; i++) {
			Percolation p = new Percolation(N);
			while(!p.percolates()) {
				p.open(rand.nextInt(N),rand.nextInt(N));
			}
			stats[i] = p.getCount() * 1.0 / (N * N);
		}
    }

    public double mean() {
        return StdStats.mean(stats);
    }

    public double stddev() {
        return StdStats.stddev(stats);
    }

    public double confidenceLow() {
        return mean() - 1.96 * stddev() / Math.sqrt(stats.length);
    }

    public double confidenceHigh() {
        return mean() + 1.96 * stddev() / Math.sqrt(stats.length);
    }

}



