public class CollisionCircleAABB implements CollisionDetector {

	public static final CollisionCircleAABB instance = new CollisionCircleAABB();
	public void detectCollision( Contact c, RigidBody a, RigidBody b ){
		c.A=b;
		c.B=a;
		CollisionAABBCircle.instance.detectCollision(c, b, a);
	}
}
