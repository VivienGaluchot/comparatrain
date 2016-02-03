package gui;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import elements.Gare;
import elements.GareHoraire;
import elements.Horaire;
import elements.Ville;

public class PanneauTrain extends JPanel{
	
	private PanneauGare Départ;
	private PanneauGare Arrivée;
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Point2D p1 = new Point2D.Double(Départ.getWidth()+Départ.getX(),Départ.getHeight()/2+Départ.getY());
		Point2D p2 = new Point2D.Double(Arrivée.getWidth()+Arrivée.getX(),Arrivée.getHeight()/2+Arrivée.getY());
		Line2D l = new Line2D.Double(p1, p2);
		Stroke s = new BasicStroke(4.0f,                      // Width
                BasicStroke.CAP_SQUARE,    // End cap
                BasicStroke.JOIN_MITER,    // Join style
                10.0f,                     // Miter limit
                new float[] {16.0f,20.0f}, // Dash pattern
                0.0f);
		g2.setStroke(s);
		g2.draw(l);
	}
	
	public PanneauTrain (GareHoraire d, GareHoraire a){
		setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		Départ = new PanneauGare(d.gare,d.gare.getVille(),d.horaire);
		Arrivée = new PanneauGare(a.gare,a.gare.getVille(),a.horaire);
		add(Départ);
		add(Box.createHorizontalGlue());
		add(Arrivée);
	}
	
	
	
	public class PanneauGare extends GroupPanel{
		private JLabel lblGare = new JLabel();
		private JLabel lblHoraires = new JLabel();
		
		public PanneauGare(Gare g, Ville v, Horaire h){
			super(v.getNom());
			lblGare.setText(g.getNom());
			lblHoraires.setText(h.toString());
			add(lblGare);
			add(lblHoraires);
			
		}
	}

}
