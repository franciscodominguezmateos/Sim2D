public class CollisionAABBLine implements CollisionDetector {

	public static final CollisionAABBLine instance = new CollisionAABBLine();
	
	double Clamp(double min,double max,double d){
		return (d<min)?min:(d>max)?max:d;
	}
	Vector2D nearestPoint(RigidAABB A,RigidLine B){
		Vector2D np=A.getVertex(0);
		double minDist=B.line.distance(np);
		for(int i=1;i<4;i++){
			Vector2D p=A.getVertex(i);
			double d=B.line.distance(p);
			if(d>minDist){//actually is farthest
				minDist=d;
				np=p;
			}
		}
		return np;
	}
	public void detectCollision( Contact c, RigidBody a, RigidBody b ){
		RigidAABB A=(RigidAABB)a;
		RigidLine B=(RigidLine)b;
		
		c.contacts.clear();
		
		Vector2D np=nearestPoint(A,B);
		
		double dist=B.line.distance(np);
		if(dist<=0){// no collision
			return;
		}
		c.penetration=dist;
		Vector2D linePoint=B.line.projectPoint(np);
		c.contacts.add(linePoint);
		c.normal=B.line.getNormal();
	}
}
