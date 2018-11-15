import java.awt.Graphics;

public class RigidAABB extends RigidBody {
	public double w ;//width
	public double w2;//width/2
	public double h ;//height
	public double h2;//height/2
	
	public RigidAABB(double x,double y){
		super();
		this.p.set(x,y);
		this.id=2;
		setWidth(10);
		setHeight(10);
	}
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
	public double minY(){return p.y-w2;}
	
	public Vector2D getVertex(int i){
		switch(i){
		case 0:
			//up right
			return new Vector2D(p.x+w2,p.y-h2);
		case 1:
			//down right
			return new Vector2D(p.x+w2,p.y+h2);
		case 2:
			//down left
			return new Vector2D(p.x-w2,p.y+h2);
		}
		//up left
		return new Vector2D(p.x-w2,p.y-h2);
	}

	@Override
	public void draw(Graphics g) {
		int x =(int)this.p.x;
		int y =(int)this.p.y;
		int w2=(int)this.w2;
		int h2=(int)this.h2;
		g.drawLine(x-w2, y-h2, x+w2, y-h2);
		g.drawLine(x+w2, y-h2, x+w2, y+h2);
		g.drawLine(x+w2, y+h2, x-w2, y+h2);
		g.drawLine(x-w2, y+h2, x-w2, y-h2);
	}
}
