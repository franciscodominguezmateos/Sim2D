public class CollisionCirclePolygon implements CollisionDetector
{

	public static final CollisionCirclePolygon instance = new CollisionCirclePolygon();

	@Override
	public void detectCollision( Contact c, RigidBody a, RigidBody b )
	{
		RigidCircle  A = (RigidCircle) a;
		RigidPolygon B = (RigidPolygon)b;

		c.contacts.clear();
		
		// Transform circle center to Polygon model space
		// Vec2 center = a->position;
		// center = B->u.Transpose( ) * (center - b->position);
		Vector2D center = A.p.sub(B.p);

		// Find edge with minimum penetration
		// Exact concept as using support points in Polygon vs Polygon
		double separation = -Double.MAX_VALUE;
		int idxFaceNormal = 0;
		for (int i = 0; i < B.localVertices.size(); ++i){
			// real s = Dot( B->m_normals[i], center - B->m_vertices[i] );
			double s =  B.localNormals.get(i).dot( center.sub( B.localVertices.get(i) ) );
			if (s > A.r){
				return;
			}
			if (s > separation)	{
				separation = s;
				idxFaceNormal = i;
			}
		}

		// Grab face's vertices
		Vector2D v1 = B.getVertex(idxFaceNormal);
		Vector2D v2 = B.getVertex(idxFaceNormal+1);

		// Check to see if center is within polygon
		if (separation < ImpulseMath.EPSILON)
		{
			// m->contact_count = 1;
			// m->normal = -(B->u * B->m_normals[faceNormal]);
			// m->contacts[0] = m->normal * A->radius + a->position;
			// m->penetration = A->radius;

			m.contactCount = 1;
			B.u.mul( B.normals[faceNormal], m.normal ).negi();
			m.contacts[0].set( m.normal ).muli( A.radius ).addi( a.position );
			m.penetration = A.radius;
			return;
		}

		// Determine which voronoi region of the edge center of circle lies within
		// real dot1 = Dot( center - v1, v2 - v1 );
		// real dot2 = Dot( center - v2, v1 - v2 );
		// m->penetration = A->radius - separation;
		float dot1 = Vector2D.dot( center.sub( v1 ), v2.sub( v1 ) );
		float dot2 = Vector2D.dot( center.sub( v2 ), v1.sub( v2 ) );
		m.penetration = A.radius - separation;

		// Closest to v1
		if (dot1 <= 0.0f)
		{
			if (Vector2D.distanceSq( center, v1 ) > A.radius * A.radius)
			{
				return;
			}

			// m->contact_count = 1;
			// Vec2 n = v1 - center;
			// n = B->u * n;
			// n.Normalize( );
			// m->normal = n;
			// v1 = B->u * v1 + b->position;
			// m->contacts[0] = v1;

			m.contactCount = 1;
			B.u.muli( m.normal.set( v1 ).subi( center ) ).normalize();
			B.u.mul( v1, m.contacts[0] ).addi( b.position );
		}

		// Closest to v2
		else if (dot2 <= 0.0f)
		{
			if (Vector2D.distanceSq( center, v2 ) > A.radius * A.radius)
			{
				return;
			}

			// m->contact_count = 1;
			// Vec2 n = v2 - center;
			// v2 = B->u * v2 + b->position;
			// m->contacts[0] = v2;
			// n = B->u * n;
			// n.Normalize( );
			// m->normal = n;

			m.contactCount = 1;
			B.u.muli( m.normal.set( v2 ).subi( center ) ).normalize();
			B.u.mul( v2, m.contacts[0] ).addi( b.position );
		}

		// Closest to face
		else
		{
			Vector2D n = B.normals[faceNormal];

			if (Vector2D.dot( center.sub( v1 ), n ) > A.r)
			{
				return;
			}

			// n = B->u * n;
			// m->normal = -n;
			// m->contacts[0] = m->normal * A->radius + a->position;
			// m->contact_count = 1;

			c.contactCount = 1;
			B.u.mul( n, c.normal ).negi();
			c.contacts[0].set( a.p ).addsi( c.normal, A.r );
		}
	}

}
