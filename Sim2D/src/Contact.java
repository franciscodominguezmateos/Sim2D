import java.util.ArrayList;
import java.util.List;


public class Contact {
	public RigidBody A;
	public RigidBody B;
	public double penetration;
	public Vector2D normal;
	public List<Vector2D> contacts;
	public double e;
	public double df;
	public double sf;
	
	public Contact(RigidBody a,RigidBody b){
		contacts=new ArrayList<>();
		A=a;
		B=b;
	}
	public boolean hasCollision(){
		return contacts.size()>0;
	}
	public void solve(){
		int ia = A.id;
		int ib = B.id;
		CollisionCallback ch=Collisions.dispatch[ia][ib];
		if(ch==null){
			System.out.print("idx="+ia+","+ib);
		}
		ch.handleCollision( this, A, B );
	}
	public void initialize(){
		e=Math.min(A.restitution, B.restitution);
		// Calculate static and dynamic friction
		// sf = std::sqrt( A->staticFriction * A->staticFriction );
		// df = std::sqrt( A->dynamicFriction * A->dynamicFriction );
		sf = (float)StrictMath.sqrt( A.staticFriction * A.staticFriction + B.staticFriction * B.staticFriction);
		df = (float)StrictMath.sqrt( A.dynamicFriction * A.dynamicFriction + B.dynamicFriction * B.dynamicFriction);

		//for (int i = 0; i < contacts.size(); i++)
		//{
			// Calculate radii from COM to contact
			// Vec2 ra = contacts[i] - A->position;
			// Vec2 rb = contacts[i] - B->position;
			//Vector2D ra = contacts.get(i).sub( A.p );
			//Vector2D rb = contacts.get(i).sub( B.p );

			// Vec2 rv = B->velocity + Cross( B->angularVelocity, rb ) -
			// A->velocity - Cross( A->angularVelocity, ra );
			//Vector2D rv = B.v.add( Vector2D.cross( B.angularVelocity, rb, new Vec2() ) ).subi( A.velocity ).subi( Vec2.cross( A.angularVelocity, ra, new Vec2() ) );

			// Determine if we should perform a resting collision or not
			// The idea is if the only thing moving this object is gravity,
			// then the collision should be performed without any restitution
			// if(rv.LenSqr( ) < (dt * gravity).LenSqr( ) + EPSILON)
			//if (rv.lengthSq() < ImpulseMath.RESTING)
			//{
			//	e = 0.0f;
			//}
		//}

	}
	public void applyImpulse(){
		if(A.infinityMass() && B.infinityMass()){
			infiniteMassCorrection();
			return;
		}
		for(int i=0;i<contacts.size();i++){
		    //Calculate radii from COM to contact
			Vector2D ra=contacts.get(i).sub(A.p);
			Vector2D rb=contacts.get(i).sub(B.p);
		    //Relative velocity
			Vector2D rv=B.v.sub(A.v); //doesn't take in acountn for w
		    //Relative velocity along que normal
			double contactVel=rv.dot(normal);
		    //Do no resolve if velocities are separating
		    if(contactVel>0)
		    	return;
			///???
			double invMassSum=A.im+B.im;//not account for invInertia
			//Calculate impulse scalar
			double j=-(1.0+e)*contactVel;
			j/=invMassSum;
			j/=contacts.size();
			//Apply impulse
			Vector2D impulse=normal.mul(j);
			A.applyImpulse(impulse.neg(),ra);
			B.applyImpulse(impulse      ,rb);
			//Friction impulse
			//TODO
			//j tangent magnitude
			//TODO
			//Do not apply tiny friction impulses
			//TODO
			//Coulumb's law
			//TODO
			//Apply friction impulse
			//TODO
		}
	}
	public void positionalCorrection(){
		final double PENETRATION_ALLOWANCE=2;
		final double PENETRATION_CORRETION=0.5;
		double correction = Math.max( penetration - PENETRATION_ALLOWANCE, 0.0f ) / (A.im + B.im) * PENETRATION_CORRETION;

		A.p.add_muli( normal, -A.im * correction );
		B.p.add_muli( normal,  B.im * correction );
	}
	public void infiniteMassCorrection(){
		A.v.set(0,0);
		B.v.set(0,0);
	}
}

