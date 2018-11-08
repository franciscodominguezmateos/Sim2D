
public class CollisionPolygonCircle implements CollisionCallback 
{

	public static final CollisionPolygonCircle instance = new CollisionPolygonCircle();
	
	@Override
	public void handleCollision(Contact c, RigidBody a, RigidBody b) 
	{
		CollisionCirclePolygon.instance.handleCollision(c, b, a); 
		
		if ( c.contactCount > 0 )
		{
			c.normal.negi();		}
	}

}
