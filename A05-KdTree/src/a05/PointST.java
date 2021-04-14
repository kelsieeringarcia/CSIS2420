package a05;

import edu.princeton.cs.algs4.RedBlackBST;
/**
 * 
 * @author Kelsie Garcia and Chad Zuniga
 *
 * @param <Value>
 */
public class PointST<Value> {

	private RedBlackBST<Point2D,Value> rb;

	// construct an empty symbol table of points
	public PointST() {
		rb = new RedBlackBST<>();
		}

	// is the symbol table empty?
	public boolean isEmpty() {
		return this.size() == 0;
	}

	// number of points
	public int size() {
		return rb.size();
	}

	// associate the value val with point p
	public void put(Point2D p, Value val) {
		if(p == null || val == null) throw new NullPointerException("Point or value cannot be null when inserting into the BST.");
		rb.put(p, val);
	}

	// value associated with point p
	public Value get(Point2D p) {
		if(p == null) throw new NullPointerException("Point cannot be null when retrieving from BST.");
		return rb.get(p);
	}

	// does the symbol table contain point p?
	public boolean contains(Point2D p) {
		if(p == null) throw new NullPointerException("Point cannot be null when checking if the BST contains the point.");
		return rb.contains(p);
	}

	// all points in the symbol table
	public Iterable<Point2D> points() {
		return rb.keys();
	}

	// all points that are inside the rectangle
	public Iterable<Point2D> range(RectHV rect) {
		if(rect == null) throw new NullPointerException("Rectangle cannot be null when checking range.");
		Point2D lo = new Point2D(rect.xmin(),rect.ymin());
		Point2D hi = new Point2D(rect.xmax(),rect.ymax());
		return rb.keys(lo, hi);
	}

	// a nearest neighbor to point p; null if the symbol table is empty
	public Point2D nearest(Point2D p) {
		if(rb == null)throw new NullPointerException("RedBlackBST cannot be null to check for the nearest point.");

		Point2D result = rb.max();
		for(Point2D el : rb.keys()) {
			if(p.distanceSquaredTo(el) < p.distanceSquaredTo(result)) {
				result = el;
			}
		}
		return result;
	}

	public static void main(String[] args) {

	}
}
