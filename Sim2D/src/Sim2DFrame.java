import java.awt.Dimension;

import javax.swing.JFrame;

public class Sim2DFrame extends JFrame {
	public Sim2DView view;

	public Sim2DView getView() {
		return view;
	}

	public void setView(Sim2DView view) {
		this.view = view;
		this.setContentPane(view);
	}
	public static void main(String args[]){
		System.out.println("Holap");
		Scene scene=new Scene(0.1,1);
		RigidCircle c=new RigidCircle(10,10,10);
		scene.add(c);
		Sim2DView view=new Sim2DView();
		view.setScene(scene);
		view.setPreferredSize(new Dimension(320,240));
		Sim2DFrame f=new Sim2DFrame();
		f.setView(view);
		f.setVisible(true);
		f.pack();
	}
}
