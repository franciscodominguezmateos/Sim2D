public class CollisionAABBAABB extends Collisions {

	public static final CollisionAABBAABB instance = new CollisionAABBAABB();
	public void handleCollision( Contact c, RigidBody a, RigidBody b ){
		RigidAABB A=(RigidAABB)a;
		RigidAABB B=(RigidAABB)b;
		Vector2D dp=B.p.sub(A.p);
		double xOverlap=A.w2+B.w2-Math.abs(dp.x);
		if(xOverlap>0){
			double yOverlap=A.h2+B.h2-Math.abs(dp.y);
			if(yOverlap>0){
				if(xOverlap>yOverlap){
					if(n.x<0)
						c.normal=new Vector2D(-1,0);
					else
						c.normal=new Vector2D( 1,0);
					c.penetration=xOverlap;
				}
				else{
					if(n.y<0)
						c.normal=new Vector2D(0,-1);
					else
						c.normal=new Vector2D(0, 1);
					c.penetration=yOverlap;
				}
			}
		}
		if(A.maxX()<B.minX() || B.maxX()<A.minX()){
			//NO collision with x axis
			double penetrationX=Math.abs(B.minX()-A.maxX());
			return;
		}
		if(A.maxY()<B.minY() || B.maxY()<A.minY()){
			//No collision with y axis
			return;
		}
	}

}
