import java.awt.Graphics;

public class RigidAABB extends RigidBody {
	public double w ;//width
	public double w2;//width/2
	public double h ;//height
	public double h2;//height/2
	
	public void setWidth(double d){
		w=d;
		w2=d/2;
	}
	public void setHeight(double d){
		h=d;
		h2=d/2;
	}
	public double maxX(){return p.x+w2;}
	public double minX(){return p.x-w2;}
	public double maxY(){return p.y+w2;}
	public double minY(){return p.y-w2:}
	

	@Override
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

}
