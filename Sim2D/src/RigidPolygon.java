import java.util.List;

public class RigidPolygon extends RigidBody{
	super();
	private List<Vector2D> vertices;
	private List<Vector2D> normals;
	
	public Vector2D getVertex(int i){
		//int N=vertices.size();
		//int idx = i % N;
		//return vertices.get(idx);
		return vertices.get(i%vertices.size());
	}
	public void computeMassCOM(double density){
		// Calculate COM and I
		COM=new Vector2D();
		area=0.0d;
		I=0.0d;
		final double INV3=1.0d/3.0d;
		
		int N=vertices.size();
		for(int i=0;i<N;i++){
			//Triangle from origin
			Vector2D v1=vertices.get(i);
			Vector2D v2=vertices.get((i+1)%N);
			//Areas
			double parallelogramArea=v1.cross(v2);
			double triangleArea=parallelogramArea*0.5d;
			area+=triangleArea;
			//Use area to weight the COM average
			double weight=triangleArea*INV3;
			COM.add_mul(v1,weight);
			COM.add_mul(v2,weight);
			// I
			double Ix2=v1.getX2()+v1.getX()*v2.getY()+v2.getX2();
			double Iy2=v1.getY2()+v1.getY()*v2.getY()+v2.getY2();
			I+=0.25d*INV3*parallelogramArea*(Ix2+Iy2);
		}
		
		COM.mul(1.0d/area);
		
		//Center vertices to COM
		for(Vector2D v:vertices)
			v.sub(COM);
		
		 m=density*area;
		im=(m!=0.0d) ? 1.0d/m : 0.0d;
		 I=I*density;
		iI=(I!=0.0d) ? 1.0d/I : 0.0d;
	}

}
