package comparatrain;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.geom.Rectangle2D;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MonPanneau extends JPanel{
	
	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		
		Rectangle2D rect = new Rectangle2D.Double(50, 50, 400, 200);
		int MX =(int) rect.getMaxX();
		int MY = (int)rect.getMaxY();
		double CX = rect.getCenterX();
		double CY = rect.getCenterY();
		g2.draw(rect);
		
	}
	
	
	
	public MonPanneau(){

	
	}

}
