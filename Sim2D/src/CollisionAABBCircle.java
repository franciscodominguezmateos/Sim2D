public class CollisionAABBCircle implements CollisionDetector {

	public static final CollisionAABBCircle instance = new CollisionAABBCircle();
	double Clamp(double min,double max,double d){
		return (d<min)?min:(d>max)?max:d;
	}
	public void detectCollision( Contact c, RigidBody a, RigidBody b ){
		RigidAABB A=(RigidAABB)a;
		RigidCircle B=(RigidCircle)b;
		
		c.contacts.clear();
		
		Vector2D dp=B.p.sub(A.p);
		// Closest point on A to the center of B
		Vector2D closest=new Vector2D(dp.x,dp.y);
		// Clamp point to edges of AABB
		closest.x=Clamp(-A.w2,A.w2,closest.x);
		closest.y=Clamp(-A.h2,A.h2,closest.y);
		boolean inside=false;
		if(dp.equals(closest)){
			inside=true;
			//Find closest axis
			if(Math.abs(dp.x)>Math.abs(dp.y)){
				//Clamp to closest extent
				if(closest.x>0)
					closest.x=A.w2;
				else
				closest.x=-A.w2;
			}
			// y axis is shorter
			else{
				//Clamp to closest extent
				if(closest.y>0)
					closest.y=A.h2;
				else
					closest.y=-A.h2;
			}
		}
		c.normal=dp.sub(closest);
		double d=c.normal.length2();
		double r=B.r;
		// Circle not inside the AABB
		if(d>r*r && !inside)
			return;
		d=Math.sqrt(d);
		//Normalize normal
		c.normal.divi(d);
		double l=c.normal.length();
		System.out.println(l);
		// Collision normal need to be flipped to point outside
		// if circle was inside the AABB
		if(inside)
			c.normal.negi();
		c.penetration=r-d;
		Vector2D contactPoint=B.p.add(c.normal.neg().mul(B.r));
		c.contacts.add(contactPoint);
	}
}
