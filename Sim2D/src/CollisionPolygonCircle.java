
public class CollisionPolygonCircle implements CollisionDetector 
{

	public static final CollisionPolygonCircle instance = new CollisionPolygonCircle();
	
	@Override
	public void detectCollision(Contact c, RigidBody a, RigidBody b) 
	{
		CollisionCirclePolygon.instance.detectCollision(c, b, a); 
		
		if ( c.contactCount > 0 )
		{
			c.normal.negi();		}
	}

}
