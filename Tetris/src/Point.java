
public class Point
{
	public double x;
	public double y;
	
	public Point(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public static double distance(Point a, Point b)
	{
		return new Vector(a, b).magnitude();
	}
	
	public Point rotateCCW(double radians, Point center)
	{
		Vector v;
		v = new Vector(this, center);
		v = v.rotateCCW(radians);		
		return Point.add(center, v);
	}
	
	public Point rotateCCW(Point center, double radians)
	{
		return rotateCCW(radians, center);
	}
	
	public Point rotateCW(double radians, Point center)
	{
		return rotateCCW(-radians, center);
	}
	
	public Point rotateCW(Point center, double radians)
	{
		return rotateCW(radians, center);
	}
	
	public static Point mid(Point a, Point b)
	{
		return div(add(a, b), 2.0);
	}
	
	public static Point add(Point a, Point b)
	{
		double x = a.x + b.x;
		double y = a.y + b.y;
		return new Point(x, y);
	}
	
	public static Point add(Point p, Vector v)
	{
		double x = p.x + v.i;
		double y = p.y + v.j;
		return new Point(x, y);
	}
	
	public static Point add(Vector v, Point p)
	{
		return add(p, v);
	}
	
	public static Point add(Vector v, Vector u)
	{
		double x = v.i + u.i;
		double y = v.j + u.j;
		return new Point(x, y);
	}
	
	public static Point sub(Point p, Point b)
	{
		double x = p.x - b.x;
		double y = p.y - b.y;
		return new Point(x, y);
	}
	
	public static Point sub(Point p, Vector v)
	{
		double x = p.x - v.i;
		double y = p.y - v.j;
		return new Point(x, y);
	}
	
	public static Point sub(Vector v, Point p)
	{
		double x = v.i - p.x;
		double y = v.j - p.y;
		return new Point(x, y);
	}
	
	public static Point div(Point p, double denominator)
	{
		double x = p.x / denominator;
		double y = p.y / denominator;
		return new Point(x, y);
	}
	
	public String toString()
	{
		return "(" + x + ", " + y + ")";
	}
}
