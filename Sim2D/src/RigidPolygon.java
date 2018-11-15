import java.awt.Graphics;
import java.util.List;

public class RigidPolygon extends RigidBody{
	public List<Vector2D> localVertices;
	public List<Vector2D> localNormals;
	
	public RigidPolygon(){
		super();
		this.id=1;
	}
	public Vector2D getLocalVertex(int i){
		//int N=vertices.size();
		//int idx = i % N;
		//return vertices.get(idx);
		return localVertices.get(i%localVertices.size());
	}
	public Vector2D getGlobalVertex(int i){
		Vector2D lv=getLocalVertex(i);
		return this.p.add(lv);
	}
	public void worOutLocalNormals(){
		int N=localVertices.size();
		if(N>1){//Must be unless two vertices
			localNormals.clear();
			for(int i=0;i<N-1;i++){
				Vector2D vn=localVertices.get(i+1).sub(localVertices.get(i)).leftNormal();
				localNormals.add(vn);
			}
			Vector2D vn=localVertices.get(0).sub(localVertices.get(N)).leftNormal();
			localNormals.add(vn);		
		}
	}
	public void addVertex(Vector2D v){
		localVertices.add(v);
		this.worOutLocalNormals();
	}
	public void computeMassCOM(double density){
		// Calculate COM and I
		COM=new Vector2D();
		area=0.0d;
		//I=0.0d;
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
			//I+=0.25d*INV3*parallelogramArea*(Ix2+Iy2);
		}
		
		COM.mul(1.0d/area);
		
		//Center vertices to COM
		for(Vector2D v:vertices)
			v.sub(COM);
		
		 m=density*area;
		im=(m!=0.0d) ? 1.0d/m : 0.0d;
		// I=I*density;
		//iI=(I!=0.0d) ? 1.0d/I : 0.0d;
	}
	Vector2D getSuportPoint(Vector2D dir){
		  double bestProjection = Double.MIN_VALUE;
		  Vector2D bestVertex=null;
		  
		  for(Vector2D v: this.vertices){
		    double projection = v.dot(dir);
		 
		    if(projection > bestProjection)
		    {
		      bestVertex = v;
		      bestProjection = projection;
		    }
		  }
		  return bestVertex;
	}
	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub
		
	}

}
