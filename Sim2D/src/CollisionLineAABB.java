public class CollisionLineAABB implements CollisionDetector {

	public static final CollisionLineAABB instance = new CollisionLineAABB();
	public void detectCollision( Contact c, RigidBody a, RigidBody b ){
		c.A=b;
		c.B=a;
		CollisionAABBLine.instance.detectCollision(c, b, a);
	}
}
