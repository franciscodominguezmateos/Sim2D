import java.awt.Graphics;

public class RigidCircle extends RigidBody{
	double r;

	public RigidCircle( double x,double y,double r )
	{
		super(x,y);
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
		g.drawOval((int)p.getX(),(int)p.getY(),(int)r,(int)r);
	}
}
