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
		for(RigidBody A:bodies)
			for(RigidBody B:bodies){
				if(A.noMass() && B.noMass())
					continue;
				Contact c=new Contact(A,B);
				c.solve();
				if(c.contactCount()>0)
					contacts.add(c);
			}
		//Integrate forces
		for(RigidBody b:bodies)
			integrateForces(b);
		//Initialize collision
		for(Contact c:contacts)
			c.initialize();
		//Solve collisions
		for(int j=0;i<iterations;j++)
			for(Contact c:contacts)
				c.applyImpulse();
		//Integrate velocities
		for(RigidBody b:bodies)
			integrateVelicity(b,dt);
		//Correct position
		for(Contact c.contacts)
			c.positionalCorrection();
		//Clear all forces
		for(RigidBody b:bodies){
			b.clearForce();
			b.clearTorque();
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
		////TODO
	}
	public void integrateVelocity(RigidBody b,double dt){
		if(b.noMass())
			return;
		b.positionAccumulate(dt);
		b.orientationAccumulate(dt);
		//=?????
		integrateForces(b,dt);
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