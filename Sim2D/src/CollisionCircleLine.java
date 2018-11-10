
public class CollisionCircleLine implements CollisionCallback {
	public static final CollisionCircleLine instance = new CollisionCircleLine();

	@Override
	public void handleCollision(Contact c, RigidBody a, RigidBody b) {
		RigidCircle A=(RigidCircle)a;
		RigidLine   B=(RigidLine)  b;
		
		c.contacts.clear();
		
		//Line circle center point distance
		double dist=B.line.distance(A.p);
		//Is circle in line? Nearest than radius
		if(dist>=-A.r){
			dist*=-1;
			c.penetration=A.r-dist;
			Vector2D linePoint=B.line.projectPoint(A.p);
			c.contacts.add(linePoint);
			c.normal=linePoint.sub(A.p);
			c.normal.neg();
			c.normal.normalize();
		}
	}
}
