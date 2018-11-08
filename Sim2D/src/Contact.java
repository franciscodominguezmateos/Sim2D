public class Contact {
	public RigidBody A;
	public RigidBody B;
	public double penetration;
	public final Vector2D normal=new Vector2D();
	public final Vector2D[] contacts = { new Vector2D(), new Vector2D() };
	public int contactCount;
	public double e;
	public double df;
	public double sf;
	public Contact(RigidBody a,RigidBody b){
		A=a;
		B=b;
	}
	public void solve(){
		int ia = A.getType().ordinal();
		int ib = B.getType().ordinal();

		Collisions.dispatch[ia][ib].handleCollision( this, A, B );

	}
	public void initialize(){
		e=Math.min(A.restitution, B.restitution);
		//TODO
	}
	public void applyImpulse(){
		//Calculate radii from COM to contact
		//Relative velocity
		//Relative velocity along que normal
		//Do no resolve if velocities are separating
		if(contactVel>0)
			return;
		///???
		//Calculate impulse scalar
		//Apply impulse
		//Friction impulse
		//j tangent magnitude
		//Do not apply tiny friction impulses
		//Coulumb's law
		//Apply friction impulse
		//TODO
	}
	public void positionalCorrection(){
		//TODO
	}
	public void infiniteMassCorrection(){
		A.velocityClear();
		B.velocityClear();
	}
}

