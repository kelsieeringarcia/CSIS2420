package ceRecursion;

public class Recursion {
	
	public static void hailstone(int seed) {
        if (seed < 0) {
            throw new IllegalArgumentException("Number needs to be positive");
        }
        
        if(seed != 1) {
        	System.out.print(seed + " ");
        	
        	if(seed % 2 == 0) {
        		seed = seed / 2;
        		hailstone(seed);
        		System.out.print("Even ");
        	}else {
        		seed = (seed * 3) + 1;
        		hailstone(seed);
        		System.out.print("Odd ");
        	}
        }else {
        	System.out.println(seed);
        }
	}
	
	public static void main(String args[]) {
		hailstone(5);
		System.out.println();
		hailstone(3);
		System.out.println();
		hailstone(16);
		System.out.println();
		hailstone(17);
		System.out.println();
		hailstone(24);
	}

}
