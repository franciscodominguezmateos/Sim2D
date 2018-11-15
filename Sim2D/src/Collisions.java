
public class Collisions {
	public static CollisionDetector[][] dispatch =
	{
		{ CollisionCircleCircle.instance, CollisionCircleLine.instance, CollisionCircleAABB.instance,CollisionCirclePolygon.instance },
		{ CollisionLineCircle.instance  ,                         null, CollisionLineAABB.instance  ,                            null},
		{ CollisionAABBCircle.instance  , null  , CollisionAABBAABB.instance  ,                            null},
		{ CollisionPolygonCircle.instance,                        null,                         null,CollisionPolygonPolygon.instance}
	};

}
