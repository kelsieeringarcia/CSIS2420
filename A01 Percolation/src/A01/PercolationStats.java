package A01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.princeton.cs.algs4.StdStats;

/**
 * @author chad.zuniga
 *
 */
public class PercolationStats {
	Random rand = new Random();
	StdStats std;
	List<Integer> stats;

	public PercolationStats(int N, int T) {
		System.out.printf("%d X %d max site: %d: ",N,N,N*N);
		stats = new ArrayList<>();
		for(int i = 0; i < T; i++) {
			Percolation p = new Percolation(N);
			while(!p.percolates()) {
				p.open(rand.nextInt(N),rand.nextInt(N));
			}
			stats.add(p.getCount());
			
		}
		stats.forEach(x -> System.out.printf("%d ",x));
    }

//    public double mean() {
//        return std.mean(stats);
//    }

    public double stddev() {
        return 0;
    }

    public double confidenceLow() {
        return 0;
    }

    public double confidenceHigh() {
        return 0;
    }

}

}
