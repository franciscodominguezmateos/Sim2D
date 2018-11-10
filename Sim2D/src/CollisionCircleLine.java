
public class CollisionCircleLine implements CollisionCallback {
	public static final CollisionCircleLine instance = new CollisionCircleLine();

	@Override
	public void handleCollision(Contact m, RigidBody a, RigidBody b) {
		RigidCircle A=(RigidCircle)a;
		RigidLine   B=(RigidLine)  b;
		//Line circle center point distance
		double dist=B.line.distance(A.p);
		//Is circle in line? Nearest than radius
		if(dist<=B.r){
			
		}
		
	}

}
