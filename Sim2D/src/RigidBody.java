import java.awt.Graphics;

public abstract class RigidBody {
	public Vector2D p;   //position
	public Vector2D v;   // linear velocity
	public Vector2D f; //accumulated force
	public double  m; // mass
	public double im; //inverse mass

	//public double   o; // orientation in radians
	//public double   w; // angular velocity
	//public double   t; // torque
	//public double  I; //moment of inertia
	//public double iI; //inverse moment of inertia
	
	public Vector2D COM; // Center of Mass
	public double area;  //rigid body area 
	
	public double staticFriction;
	public double dynamicFriction;
	public double restitution;
	
	//color
	public double r,g,b;
	
	public int id;
	
	public RigidBody(){
		p=new Vector2D( 0, 0 );
		v=new Vector2D( 0, 0 );
		f=new Vector2D( 0, 10 );
		 m=1;
		im=1;
		//o = 0;
		//w = 0;
		//t = 0;
		//p = Random( -PI, PI );
		staticFriction = 0.5;
		dynamicFriction = 0.3;
		restitution = 0.5;
	}
	public void applyforce(Vector2D f){
		this.f.addi(f);
	}
	public void applyImpulse(Vector2D impulse,Vector2D contactVector){
		v.add_muli(impulse,im);
		//w+=iI*contactVector.cross(impulse);
	}
	public void setStatic(){
		 m=Double.MAX_VALUE;
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
	public void clearForce(){f.set(0,0);}
	public abstract void draw(Graphics g);
}
