
public class Line {
	Vector2D p0,p1;
	public double a,b,c;
	public Line(Vector2D p0,Vector2D p1){
		Vector2D v=p1.sub(p0).normalize();
		Vector2D vn=new Vector2D(-v.y,v.x);
		this.p0=p0;
		this.p1=p1;
		a=vn.x;
		b=vn.y;
		c=-vn.dot(p0);
	}
	public double distance(Vector2D p){
		return a*p.x+b*p.y+c;
	}
	public Vector2D projectPoint(Vector2D p){
		Vector2D vp=p.sub(p0);
		Vector2D vn=this.getNormal();
		//vProjected=vp-vp*vn;
		Vector2D vProjected=vp.sub(vp.projectOn(vn));
		Vector2D point=p0.add(vProjected);
		return point;
	}
	public Vector2D getNormal(){
		return new Vector2D(a,b);
	}
}
