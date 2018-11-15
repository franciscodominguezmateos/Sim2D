public class CollisionAABBAABB implements CollisionDetector{

	public static final CollisionAABBAABB instance = new CollisionAABBAABB();
	public void detectCollision( Contact c, RigidBody a, RigidBody b ){
		RigidAABB A=(RigidAABB)a;
		RigidAABB B=(RigidAABB)b;
		Vector2D dp=B.p.sub(A.p);
		double xOverlap=A.w2+B.w2-Math.abs(dp.x);
		if(xOverlap>0){
			double yOverlap=A.h2+B.h2-Math.abs(dp.y);
			if(yOverlap>0){
				if(xOverlap>yOverlap){
					if(dp.x<0){
						c.normal=new Vector2D(-1,0);
						Vector2D contactPoint=new Vector2D(A.p.x-A.w2,A.p.y);
						c.contacts.add(contactPoint);
					}
					else{
						c.normal=new Vector2D( 1,0);
						Vector2D contactPoint=new Vector2D(A.p.x+A.w2,A.p.y);
						c.contacts.add(contactPoint);
					}
					c.penetration=xOverlap;
				}
				else{
					if(dp.y<0){
						c.normal=new Vector2D(0,-1);
						Vector2D contactPoint=new Vector2D(A.p.x,A.p.y-A.h2);
						c.contacts.add(contactPoint);
					}
					else{
						c.normal=new Vector2D(0, 1);
						Vector2D contactPoint=new Vector2D(A.p.x-A.w2,A.p.y+A.h2);
						c.contacts.add(contactPoint);
					}
					c.penetration=yOverlap;
				}
			}
		}
	}

}
