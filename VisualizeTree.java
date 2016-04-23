package linuxgitmsr;
import javax.swing.JFrame;
import java.awt.*;
public class VisualizeTree {
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
					
		DrawTree dt = new DrawTree();
		//dt.setBounds(0, 0, 2800, 1400);
		System.out.println("yyyyyyyyyyyyyyyy: "+dt.getInsets());
		frame.add(dt);
		frame.setSize(2000,1000);
		frame.setTitle("Commit Tree Visualizer");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
