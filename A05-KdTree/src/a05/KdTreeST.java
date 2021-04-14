package a05;

import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;
/**
 * 
 * @author Kelsie Garcia and Chad Zuniga
 *
 * @param <Value>
 */
public class KdTreeST<Value> {
	
	private int size;
	private Node root;

	private class Node {
		private Point2D p; // the point
		private Value value; // the symbol table maps the point to this value
		private RectHV rect; // the axis-aligned rectangle corresponding to this node
		private boolean isVertical;
		private Node lb; // the left/bottom subtree
		private Node rt; // the right/top subtree

		private Node(Point2D p, Value val, RectHV rect, boolean isVertical) {
			this.p = p;
			this.value = val;
			this.rect = rect;
			this.isVertical = isVertical;
			
			this.lb = null;
			this.rt = null;
		}
	}

	/**
	 * Constructs the kd tree
	 */
	public KdTreeST() {
		size = 0;
		root = null;
	}

	/**
	 * This method checks to see if the kd tree is empty
	 * @return 
	 */
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	 * 
	 * @return the size of the kd tree
	 */
	public int size() {
		return size;
	}
	
	/**
	 * This method puts the point within the kd tree
	 * @param p
	 * @param val
	 */
	public void put(Point2D p, Value val) {
		if(p == null || val == null) throw new NullPointerException("Point or Value can't be null when Putting a Point within the KDTreeST");
		
		if(root == null) {
			root = new Node(
					p, 
					val, 
					new RectHV(-Double.MAX_VALUE,-Double.MAX_VALUE,Double.MAX_VALUE,Double.MAX_VALUE),
					true
					);
		} else {
			root = put(p, val, root, null, true);
		}
		size++;
	}
	
	private Node put(Point2D p, Value val, Node n, Node parent, boolean isVertical) {
		if (n == null) {
			return new Node(
					p, 
					val, 
					createRect(p,parent,isVertical),
					isVertical
					);
		}
		
		double cmp;
		if(isVertical) {
			cmp = p.x() - n.p.x();
		} else{
			cmp = p.y() - n.p.y();
		}
		
		if(cmp < 0) {
			n.lb = put(p, val, n.lb, n, !isVertical);
		} else if (cmp > 0) {
			n.rt = put(p, val, n.rt, n, !isVertical);
		} else if (cmp == 0) {
			if(n.p.equals(p)) {
				n.value = val;
				size--;
			} else {
				n.rt = put(p, val, n.rt, n, !isVertical);
			}
		}
		
		return n;
	}
	
	private RectHV createRect(Point2D p, Node parent, boolean isVertical) {	
		double cmp;
		if(!isVertical) {
			cmp = p.x() - parent.p.x();
		} else{
			cmp = p.y() - parent.p.y();
		}
		
		if(isVertical) {
			if(cmp < 0) {
				return new RectHV(parent.rect.xmin(),parent.rect.ymin(),parent.rect.xmax(),parent.p.y());
			}
			else {
				return new RectHV(parent.rect.xmin(),parent.p.y(),parent.rect.xmax(),parent.rect.ymax());
			}
		}
		else {
			if(cmp < 0) {
				return new RectHV(parent.rect.xmin(),parent.rect.ymin(),parent.p.x(),parent.rect.ymax());
			}
			else{ 
				return new RectHV(parent.p.x(),parent.rect.ymin(),parent.rect.xmax(),parent.rect.ymax());
			}
		}
	}
	
	/**
	 * This method gets the value of the given point of the tree
	 * @param p
	 * @return
	 */
	public Value get(Point2D p) {
		if(p == null) throw new NullPointerException("Point can't be null when Getting a Point within the KDTreeST");
		return get(p, root, true);
	}
	
	private Value get(Point2D p, Node n, boolean isVertical) {	
		if(n == null) return null;
		
		double cmp;
		if(isVertical) {
			cmp = p.x() - n.p.x();
		} else{
			cmp = p.y() - n.p.y();
		}
		
		if(cmp < 0) {
			return get(p, n.lb, !isVertical);
		}
		else if(cmp == 0) {
			if (n.p.equals(p)) {
				return n.value;
			}
			return get(p, n.rt, !isVertical);
		} else {
			return get(p, n.rt, !isVertical);
		}
		
	}
	
	/**
	 * This method checks if the kd tree contains the given point
	 * @param p
	 * @return
	 */
	public boolean contains(Point2D p) {
		if(p == null) throw new NullPointerException("Point can't be null when checking to see if KdTreeST contains it");
		return get(p) != null;
	}
	
	/**
	 * This method returns an iterable queue of the given points of the kd tree
	 * @return
	 */
	public Iterable<Point2D> points() {
		Queue<Point2D> result = new Queue<>();
		if(root == null) return result;
		
		Queue<Node> nodes = new Queue<>();
		nodes.enqueue(root);
		
		Node n;
		while(!nodes.isEmpty()) {
			n = nodes.dequeue();
			result.enqueue(n.p);
			if(n.lb != null) nodes.enqueue(n.lb);
			if(n.rt != null) nodes.enqueue(n.rt);
		}
		
		return result;
	}

	/**
	 * This method will search for points within a given rectangle
	 * @param rect
	 * @return a list of points within a range of a given rectangle
	 */
	public Iterable<Point2D> range(RectHV rect) {
		if(rect == null) throw new NullPointerException("Rectangle can't be null when returning all Points in Range");
		Queue<Point2D> result = new Queue<>();
		range(rect, root, result);
		return result;
	}
	
	private void range(RectHV rect, Node n, Queue<Point2D> p) {
		if(n == null) return;
		
		if(!rect.intersects(n.rect)) return;
		if(rect.contains(n.p)) p.enqueue(n.p);
		range(rect, n.lb, p);
		range(rect, n.rt, p);
	}
	
	/*
	 * This method will return the point nearest to the given point
	 */
	public Point2D nearest(Point2D p) {
		if(p == null) throw new NullPointerException("Point can't be null when trying to find nearest");

		return nearest(p, root, root.p);
	}
	
	private Point2D nearest(Point2D p, Node n, Point2D currentNearest) {
		if(n == null) return currentNearest;
		
		if(n.rect.distanceSquaredTo(p) > currentNearest.distanceSquaredTo(p)) return currentNearest;
		if(p.distanceSquaredTo(n.p) < p.distanceSquaredTo(currentNearest)) currentNearest = n.p;
		
		if(n.lb != null && n.lb.rect.contains(p)) {
			currentNearest = nearest(p, n.lb, currentNearest);
		} else {
			currentNearest = nearest(p, n.rt, currentNearest);
		}
		
		return currentNearest;
	}
	
	/**
	 * TEST CLIENT
	 * @param args
	 */
	public static void main(String[] args) {
		KdTreeST<Integer> kd = new KdTreeST<>();
		Point2D pt = new Point2D(2,5); 
		kd.put(new Point2D(2,3), 1);
		kd.put(new Point2D(3,3), 3);
		kd.put(new Point2D(7,3), 12);
		kd.put(new Point2D(2,5), 100);
		
		System.out.println(kd.get(pt));


	}

}
