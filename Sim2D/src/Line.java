
public class Line {
	public double a,b,c;
	public Line(Vector2D p0,Vector2D p1){
		Vector2D v=p1.sub(p0).normalize();
		Vector2D vn=new Vector2D(-v.y,v.x);
		a=vn.x;
		b=vn.y;
		c=-vn.dot(p0);
	}
	public double distance(Vector2D p){
		return a*p.x+b*p.y+c;
	}
	public Vector2D getNormal(){
		return new Vector2D(a,b);
	}
}
