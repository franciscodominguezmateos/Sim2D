import java.awt.Dimension;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JFrame;

public class Sim2DFrame extends JFrame {
	private Timer t;
	private TimerTask task;
	public Sim2DView view;
	public Scene scene;

	public Sim2DFrame(){
		t=new Timer();
		task=new TimerTask(){
			public void run(){
				scene.step();
				view.repaint();
			}
		};
	}
	public void start(){
		t.scheduleAtFixedRate(task, 100, 100);		
	}
	public Sim2DView getView() {
		return view;
	}

	public void setView(Sim2DView view) {
		this.view = view;
		this.setContentPane(view);
	}
	public static void main(String args[]){
		System.out.println("Hola simulator Lines");
		Line l=new Line(new Vector2D(0,0),new Vector2D(1,1));
		System.out.println(l.distance(new Vector2D(2,2)));
		System.out.println(l.getNormal());
		System.out.println(l.getNormal().length());
		System.out.println(l.distance(new Vector2D(1,1.5)));
		System.out.println(l.distance(new Vector2D(1,0.5)));
		Sim2DFrame f=new Sim2DFrame();
		f.scene=new Scene(0.1,1);
		RigidCircle c=new RigidCircle(10,10,10);
		c.v=new Vector2D(10,20);
		f.scene.add(c);
		RigidLine rl=new RigidLine(0,200,320,200);
		f.scene.add(rl);
		Sim2DView view=new Sim2DView();
		view.setScene(f.scene);
		view.setPreferredSize(new Dimension(320,240));
		f.setView(view);
		f.start();
		f.setVisible(true);
		f.pack();
	}
}
