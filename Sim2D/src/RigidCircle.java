import java.awt.Graphics;

public class RigidCircle extends RigidBody{
	double r;

	public RigidCircle( double x,double y,double r )
	{
		super();
		this.p.set(x,y);
		this.id=0;
		this.r = r;
	}

	@Override
	public RigidBody clone()
	{
		return new RigidCircle( p.getX(),p.getY(),r );
	}

	public void initialize()
	{
		computeMass( 1.0f );
	}

	public void computeMass( double density )
	{
		 m = Math.PI * r * r * density;
		im = (m != 0.0f) ? 1.0f / m : 0.0f;
		//body.inertia = body.mass * radius * radius;
		//body.invInertia = (body.inertia != 0.0f) ? 1.0f / body.inertia : 0.0f;
	}
	public void draw(Graphics g){
		int r=(int)this.r;
		int r2=r*2;
		g.drawOval((int)p.getX()-r,(int)p.getY()-r,r2,r2);
	}
}
