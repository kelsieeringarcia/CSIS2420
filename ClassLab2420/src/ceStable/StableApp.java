package ceStable;


import edu.princeton.cs.algs4.Insertion;
import edu.princeton.cs.algs4.Selection;

public class StableApp {

	public static void main(String[] args) {
		Rectangle[] r = new Rectangle[11];
		r[0] = new Rectangle(4,2);
		r[1] = new Rectangle(3,5);
		r[2] = new Rectangle(4,3);
		r[3] = new Rectangle(6,2);
		r[4] = new Rectangle(3,4);
		r[5] = new Rectangle(4,4);
		r[6] = new Rectangle(6,4);
		r[7] = new Rectangle(12,2);
		r[8] = new Rectangle(2,4);
		r[9] = new Rectangle(4,6);
		r[10] = new Rectangle(2,12);
		
		System.out.print("rectangles : [");

		printRectangles(r);
		
		System.out.println();
		System.out.println();
		System.out.println("Stable Sort: ");
		
		System.out.print("sorted by length: [");
		Insertion.sort(r);
		printRectangles(r);
		System.out.println();
		System.out.print("sorted by area: [" );
		Insertion.sort(r, Rectangle.BY_AREA);
		printRectangles(r);
		System.out.println();
		System.out.println();
		System.out.println("Not-Stable Sort: ");
		System.out.print("sorted by length: [");
		Selection.sort(r);
		printRectangles(r);
		System.out.println();
		System.out.print("sorted by area: [" );
		Selection.sort(r, Rectangle.BY_AREA);
		printRectangles(r);
	}

	private static void printRectangles(Rectangle[] r) {
		for(int i = 0; i < r.length; i ++) {
			if(i == r.length - 1) {
				System.out.print(r[i] + "]");
			}else {
				System.out.print(r[i] + ", ");
			}
		}
	}

}
