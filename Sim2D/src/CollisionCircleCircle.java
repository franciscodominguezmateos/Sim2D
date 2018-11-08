public class CollisionCircleCircle implements CollisionCallback
{

	public static final CollisionCircleCircle instance = new CollisionCircleCircle();

	@Override
	public void handleCollision( Contact c, RigidBody a, RigidBody b )
	{
		RigidCircle A = (RigidCircle)a;
		RigidCircle B = (RigidCircle)b;

		// Calculate translational vector, which is normal
		// Vec2 normal = b->position - a->position;
		Vector2D normal = b.p.sub( a.p );

		// real dist_sqr = normal.LenSqr( );
		// real radius = A->radius + B->radius;
		double dist_sqr = normal.length2();
		double radius = A.r + B.r;

		// Not in contact
		if (dist_sqr >= radius * radius)
		{
			c.contactCount = 0;
			return;
		}

		double distance = Math.sqrt( dist_sqr );

		c.contactCount = 1;

		if (distance == 0.0f)
		{
			// m->penetration = A->radius;
			// m->normal = Vec2( 1, 0 );
			// m->contacts [0] = a->position;
			c.penetration = A.r;
			c.normal.set( 1.0f, 0.0f );
			c.contacts[0].set( a.p );
		}
		else
		{
			// m->penetration = radius - distance;
			// m->normal = normal / distance; // Faster than using Normalized since
			// we already performed sqrt
			// m->contacts[0] = m->normal * A->radius + a->position;
			c.penetration = radius - distance;
			c.normal.set( normal ).divi( distance );
			c.contacts[0].set( c.normal ).muli( A.r ).addi( a.p );
		}
	}

}
