import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class Scene {
	public double dt;
	public int iterations;
	public List<RigidBody> bodies;
	public List<Contact> contacts;
	public Scene(double dt, int iterations){
		bodies  =new ArrayList<RigidBody>();
		contacts=new ArrayList<Contact>();
		this.dt=dt;
		this.iterations=iterations;
	}
	public void step(){
		//Clear all forces
		for(RigidBody b:bodies){
			b.clearForce();
		}
		//Integrate forces
		for(RigidBody b:bodies)
			integrateForces(b);
		//Integrate velocities
		for(RigidBody b:bodies)
			integrateVelocity(b);
	}
	public void add(RigidBody b){
		bodies.add(b);
	}
	public void clear(){
		contacts.clear();
		bodies.clear();
	}
	public void integrateForces(RigidBody b){
		if(b.infinityMass())
			return;
		//a=f/m;
		Vector2D a=b.f.div(b.m);
		//dv=a*dt;
		Vector2D dv=a.mul(dt);
		//v=v+dv;
		b.v.addi(dv);
	}
	public void integrateVelocity(RigidBody b){
		if(b.infinityMass())
			return;
		//dp=v*dt;
		Vector2D dp=b.v.mul(dt);
		//p=p+dp;
		b.p.addi(dp);
	}
	void draw(Graphics g){
		//Draw Bodies
		for(RigidBody b:bodies){
			b.draw(g);
		}
		//Draw contacts
		//Draw contacts normals
	}
}