import java.awt.Graphics;

public abstract class RigidBody {
	protected Vector2D p;   //position
	protected Vector2D v;   // linear velocity
	protected Vector2D f; //accumulated force
	protected double  m; // mass
	protected double im; //inverse mass

	//private double   o; // orientation in radians
	//private double   w; // angular velocity
	//private double   t; // torque
	//protected double  I; //moment of inertia
	//protected double iI; //inverse moment of inertia
	
	protected Vector2D COM; // Center of Mass
	protected double area;  //rigid body area 
	
	private double staticFriction;
	private double dynamicFriction;
	private double restitution;
	
	//color
	private double r,g,b;
	
	public RigidBody(double x,double y){
		p=new Vector2D( x, y );
		v=new Vector2D( 0, 0 );
		f=new Vector2D( 0, 0 );
		//o = 0;
		//w = 0;
		//t = 0;
		//p = Random( -PI, PI );
		staticFriction = 0.5;
		dynamicFriction = 0.3;
		restitution = 0.2;
	}
	public void applyforce(Vector2D f){
		this.f.add_mul(f, 1);
	}
	public void applyImpulse(Vector2D impulse,Vector2D contactVector){
		v.add_mul(impulse,im);
		//w+=iI*contactVector.cross(impulse);
	}
	public void setStatic(){
		 m=0;
		im=0;
		// I=0;
		//iI=0;
	}
	public boolean noMass(){
		return m==0;
	}
	public boolean infinityMass(){
		return im==0;
	}
	public abstract void draw(Graphics g);
}
