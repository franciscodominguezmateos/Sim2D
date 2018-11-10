import java.awt.Graphics;

public class RigidLine extends RigidBody {
	Vector2D p1;
	public Line line;
	public RigidLine(double x0,double y0,double x1,double y1){
		super();
		this.id=1;
		this.im=0;
		this.m=Double.MAX_VALUE;
		p =new Vector2D(x0,y0);
		p1=new Vector2D(x1,y1);
		line=new Line(p,p1);
	}
	@Override
	public void draw(Graphics g) {
		g.drawLine((int)p.x,(int) p.y,(int) p1.x,(int)p1.y);
	}
}
