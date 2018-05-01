
public class Point {

	double x=0,y=0;
	Point(double x,double y)
	{
		this.x = x;
		this.y = y;
	}
	boolean equals(Point p)
	{
		if(p.x==this.x&&p.y==this.y)
			return true;
		else return false;
	}
	void setX(double x)
	{
		this.x = x;
	}
	void setY(double y)
	{
		this.y = y;
	}
	double getX()
	{
		return x;
	}
	double getY()
	{
		return y;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Point a = new Point(1,2);
		Point b = new Point(1,2);
		Line l1 = new Line(a,b);
		l1.setLine();
		System.out.println(l1.k);
	}

}
