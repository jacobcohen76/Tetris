package tetris.util;

public class Vector
{
	public double i;
	public double j;
	
	private Vector()
	{
		this.i = 0d;
		this.j = 0d;
	}
	
	public Vector(double i, double j)
	{
		this.i = i;
		this.j = j;
	}
	
	public Vector normalize()
	{
		return Vector.div(this, magnitude());
	}
	
	public Vector(Point a, Point b)
	{
		this.i = b.x - a.x;
		this.j = b.y - a.y;
	}
	
	public double magnitude()
	{
		return Math.sqrt(Vector.dot(this, this));
	}
	
	public boolean equals(Object o)
	{
		if(o == this)
			return true;
		else if(!(o instanceof Vector))
			return false;
		
		Vector v = (Vector) o;
		
		return Double.compare(this.i, v.i) == 0 && Double.compare(this.j, v.j) == 0; 
	}
	
	public Vector rotateCCW(double radians)
	{
		double sin = Math.sin(radians);
		double cos = Math.cos(radians);
		Vector transposed = new Vector();
		transposed.i = i * cos - j * sin;
		transposed.j = i * sin + j * cos;
		return transposed;
	}
	
	public static Vector getNormalOf(Vector v)
	{
		return new Vector(-v.j, v.i);
	}
	
	public Vector rotateCW(double radians)
	{
		return rotateCCW(-radians);
	}
	
	public static double dot(Vector a, Vector b)
	{
		return ((a.i * b.i) + (a.j * b.j));
	}
	
	public static double cross(Vector a, Vector b)
	{
		return ((a.i * b.j) - (a.j * b.i));
	}
	
	public static boolean isOrthogonal(Vector a, Vector b)
	{
		return Vector.dot(a, b) == 0;
	}
	
	public static double getAngleBetween(Vector u, Vector v)
	{
		double radians;
		radians = Vector.dot(u, v);
		radians /= Vector.dot(u, u) * Vector.dot(v, v);		
		return Math.acos(radians);
	}
	
	public static Vector proj(Vector v, Vector a)
	{
		double coefficient;
		coefficient = Vector.dot(a, v);
		coefficient /= Vector.dot(v, v);
		return mult(coefficient, v);
	}
	
	public static Vector add(Vector a, Vector b)
	{
		double i = a.i + b.i;
		double j = a.j + b.j;
		return new Vector(i, j);
	}
	
	public static Vector add(Vector v, Point p)
	{
		double i = v.i + p.x;
		double j = v.j + p.y;
		return new Vector(i, j);
	}
	
	public static Vector add(Point p, Vector v)
	{
		return add(v, p);
	}
	
	public static Vector sub(Vector a, Vector b)
	{
		double i = a.i - b.i;
		double j = a.j - b.j;
		return new Vector(i, j);
	}
	
	public static Vector sub(Vector v, Point p)
	{
		double i = v.i - p.x;
		double j = v.j - p.y;
		return new Vector(i, j);
	}
	
	public static Vector sub(Point p, Vector v)
	{
		double i = p.x - v.i;
		double j = p.y - v.j;
		return new Vector(i, j);
	}
	
	public static Vector sub(Point a, Point b)
	{
		double i = a.x - b.x;
		double j = a.y - b.y;
		return new Vector(i, j);
	}
	
	public static Vector mult(double coefficient, Vector v)
	{
		double i = coefficient * v.i;
		double j = coefficient * v.j;
		return new Vector(i, j);
	}
	
	public static Vector mult(Vector v, double coefficient)
	{
		return mult(coefficient, v);
	}
	
	public static Vector div(Vector v, double denominator)
	{
		double i = v.i / denominator;
		double j = v.j / denominator;
		return new Vector(i, j);
	}
	
	public String toString()
	{
		return "<" + i + ", " + j + ">";
	}
	
	public Vector getIterableForm()
	{
		Vector iterable = this.round();
		iterable.i = Math.abs(iterable.i) > 0 ? Math.abs(iterable.i) / iterable.i  : 0;
		iterable.j = Math.abs(iterable.j) > 0 ? Math.abs(iterable.j) / iterable.j  : 0;
		return iterable;
	}
	
	public Vector round()
	{
		return new Vector(Math.round(i), Math.round(j));
	}
	
	public static final Vector ZERO = new Vector(0, 0);
	
	public static final Vector EAST = new Vector(+1, 0);
	public static final Vector WEST = new Vector(-1, 0);
	public static final Vector NORTH = new Vector(0, +1);
	public static final Vector SOUTH = new Vector(0, -1);
	
	public static final Vector NORTHEAST = Vector.add(NORTH, EAST);
	public static final Vector NORTHWEST = Vector.add(NORTH, WEST);
	public static final Vector SOUTHWEST = Vector.add(SOUTH, WEST);
	public static final Vector SOUTHEAST = Vector.add(SOUTH, EAST);
}
