
public class Line {
	Point a,b;
	public double k = 0;
	Line(Point a,Point b)
	{
		this.a = new Point(a.getX(),a.getY());
		this.b = new Point(b.getX(),b.getY());
	}
	Line setLine()
	{
		
			try{
					if(this.a.equals(this.b))throw new Exception();
			}
			catch(Exception e)
			{
				//System.out.println(e.toString());
				System.out.println("两个点重合");
			}
			finally{
				k = (a.y - b.y)/(a.x - b.x);
			}
			return this;
	}
}