package a05;

public class RectHV {
	private double xmin;
	private double ymin;
	private double xmax;
	private double ymax;
	
	// construct the rectangle [xmin, xmax] x [ymin, ymax] 
	public RectHV(double xmin, double ymin, double xmax, double ymax) {
		super();
		this.xmin = xmin;
		this.ymin = ymin;
		this.xmax = xmax;
		this.ymax = ymax;
        if (Double.isNaN(xmin) || Double.isNaN(xmax)) {
            throw new IllegalArgumentException("x-coordinate is NaN: " + toString());
        }
        if (Double.isNaN(ymin) || Double.isNaN(ymax)) {
            throw new IllegalArgumentException("y-coordinate is NaN: " + toString());
        }
        if (xmax < xmin) {
            throw new IllegalArgumentException("xmax < xmin: " + toString());
        }
        if (ymax < ymin) {
            throw new IllegalArgumentException("ymax < ymin: " + toString());
        }
	}

	/**
	 * The minimum x-coordinate of rectangle 
	 * @return the xmin
	 */
	public double xmin() {
		return xmin;
	}

	/**
	 * The minimum y-coordinate of rectangle
	 * @return the ymin
	 */
	public double ymin() {
		return ymin;
	}

	/**
	 * The maximum x-coordinate of rectangle
	 * @return the xmax
	 */
	public double xmax() {
		return xmax;
	}

	/**
	 * The maximum y-coordinate of rectangle
	 * @return the ymax
	 */
	public double ymax() {
		return ymax;
	}
	
	// does this rectangle contain the point p (either inside or on boundary)? 
	public boolean contains(Point2D p) {
        return (p.x() >= xmin) && (p.x() <= xmax)
                && (p.y() >= ymin) && (p.y() <= ymax);
	}
	
	// does this rectangle intersect that rectangle (at one or more points)? 
	public boolean intersects(RectHV that) {
	       return this.xmax >= that.xmin && this.ymax >= that.ymin
	               && that.xmax >= this.xmin && that.ymax >= this.ymin;
	}
	
	// square of Euclidean distance from point p to closest point in rectangle 
	public  double distanceSquaredTo(Point2D p) {
        double dx = 0.0, dy = 0.0;
        if      (p.x() < xmin) dx = p.x() - xmin;
        else if (p.x() > xmax) dx = p.x() - xmax;
        if      (p.y() < ymin) dy = p.y() - ymin;
        else if (p.y() > ymax) dy = p.y() - ymax;
        return dx*dx + dy*dy;
	}

	@Override
	public int hashCode() {
        int hash1 = ((Double) xmin).hashCode();
        int hash2 = ((Double) ymin).hashCode();
        int hash3 = ((Double) xmax).hashCode();
        int hash4 = ((Double) ymax).hashCode();
        return 31*(31*(31*hash1 + hash2) + hash3) + hash4;
	}

	// does this rectangle equal that object?
	@Override
	public boolean equals(Object other) {
        if (other == this) return true;
        if (other == null) return false;
        if (other.getClass() != this.getClass()) return false;
        RectHV that = (RectHV) other;
        if (this.xmin != that.xmin) return false;
        if (this.ymin != that.ymin) return false;
        if (this.xmax != that.xmax) return false;
        if (this.ymax != that.ymax) return false;
        return true;
	}

	@Override
	public String toString() {
		return "RectHV [xmin=" + xmin + ", ymin=" + ymin + ", xmax=" + xmax + ", ymax=" + ymax + "]";
	}
	

}
