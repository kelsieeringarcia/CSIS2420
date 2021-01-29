package a01;

public class TestApp {
	public static void main(String[] args) {
//		Percolation p = new Percolation(5);
//		
//		Percolation.showGrid(p);
//		
//		p.open(0,1);
//		p.open(1,1);
//		p.open(1,2);
//		p.open(3,2);
//		p.open(4,2);
//		
//		
//		
//		System.out.println(p.percolates());
//		
//		Percolation.showGrid(p);
//		
//		p.open(2,2);
//		p.open(4,4);
//		System.out.println(p.percolates());
//		
//		Percolation.showGrid(p);
		
		
		PercolationStats ps = new PercolationStats(20,40);
		System.out.println();
		System.out.printf("mean()			= %f\n", ps.mean());
		System.out.printf("stddev()		= %f \n", ps.stddev());
		System.out.printf("confidenceLow()		= %f \n", ps.confidenceLow());
		System.out.printf("confidenceHigh()	= %f \n", ps.confidenceHigh());
		
		
		
		
	}

}

