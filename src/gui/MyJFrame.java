package gui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MyJFrame extends JFrame{
	public void positionner(){
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension dim = tk.getScreenSize();
		int screenW = dim.width;
		int screenH = dim.height;

		pack();
		setBounds((screenW-this.getWidth())/2,(screenH-this.getHeight())/2,this.getWidth(),this.getHeight());
	}
	
	public void afficher(){
		setVisible(true);
	}
}
