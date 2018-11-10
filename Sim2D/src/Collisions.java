
public class Collisions {
	public static CollisionCallback[][] dispatch =
	{
		{ CollisionCircleCircle.instance, CollisionCircleLine.instance, CollisionCirclePolygon.instance },
		{ CollisionLineCircle.instance  ,                         null,                             null},
		{ CollisionPolygonCircle.instance,                        null,CollisionPolygonPolygon.instance }
	};

}
