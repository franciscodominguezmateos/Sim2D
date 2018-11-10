
public class Collisions {
	public static CollisionCallback[][] dispatch =
	{
		{ CollisionCircleCircle.instance, CollisionCircleLine.instance },
		{ CollisionLineCircle.instance, CollisionCirclePolygon.instance },
		{ CollisionPolygonCircle.instance, CollisionPolygonPolygon.instance }
	};

}
