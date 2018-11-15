import java.awt.Color;
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
		//Clear contacts
		contacts.clear();
		//Work out contacts
		for(int i=0;i<bodies.size();i++){
			RigidBody a=bodies.get(i);
			for(int j=i+1;j<bodies.size();j++){
				RigidBody b=bodies.get(j);
				if(a.infinityMass() && b.infinityMass()){
					continue;
				}
				Contact c=new Contact(a,b);
				c.solve();
				if(c.hasCollision()){
					contacts.add(c);
				}
			}
		}
		//Clear all forces
		for(RigidBody b:bodies){
			b.clearForce();
		}
		//Set gravity
		for(RigidBody b:bodies){
			b.f=new Vector2D(0,9.8);
		}
		//Integrate forces
		for(RigidBody b:bodies)
			integrateForces(b);
		//Init contacts
		for(Contact c:contacts){
			c.initialize();
		}
		for(int i=0;i<iterations;i++){
			for(Contact c:contacts){
				c.applyImpulse();
			}
		}
		//Integrate velocities
		for(RigidBody b:bodies)
			integrateVelocity(b);
		//Correct positions
		for(Contact c:contacts){
			c.positionalCorrection();
		}
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
		int contactSize=3;
		g.setColor(Color.RED);
		for(Contact c:contacts)
			for(Vector2D v:c.contacts)
				g.drawOval((int)v.x-contactSize/2,(int)v.y-contactSize/2, contactSize, contactSize);
		//Draw contacts normals
	}
}