
public class CollisionLineCircle implements CollisionDetector {
	public static final CollisionLineCircle instance = new CollisionLineCircle();

	@Override
	public void detectCollision(Contact c, RigidBody a, RigidBody b) {
		c.A=b;
		c.B=a;
		CollisionCircleLine.instance.detectCollision(c, b, a);
	}
}
