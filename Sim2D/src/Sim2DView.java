import java.awt.Graphics;

import javax.swing.JPanel;

public class Sim2DView extends JPanel {
	private Scene scene;
	public void paint(Graphics g){
		super.paint(g);
		scene.draw(g);
	}
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
}
