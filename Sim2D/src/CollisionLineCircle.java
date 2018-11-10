
public class CollisionLineCircle implements CollisionCallback {
	public static final CollisionLineCircle instance = new CollisionLineCircle();

	@Override
	public void handleCollision(Contact c, RigidBody a, RigidBody b) {
		c.A=b;
		c.B=a;
		CollisionCircleLine.instance.handleCollision(c, b, a);
	}
}
