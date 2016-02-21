package gui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

import elements.Horaire;
import offre.Offre;
import offre.OffreSimple;

@SuppressWarnings("serial")
public class PanneauOffre extends JPanel{	
	Color[] couleur = {new Color(16424219),new Color(564687),new Color(7019114),new Color(7771648),new Color(16747652),new Color(14366112),new Color(9174374)};
	Offre offre;	
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D)g;
		Stroke s = new BasicStroke(3.0f,BasicStroke.CAP_BUTT,BasicStroke.JOIN_MITER);
		g2.setStroke(s);
		
		Double height =  this.getHeight()/2.;
		Double longeur = (double) this.getWidth();
		Horaire Td = offre.getDepart().horaire;
		Horaire Ta = offre.getArrivee().horaire;
		Double echelle = longeur/(Td.until(Ta));
		
		g2.drawString(offre.getDepart().gare.getNom(), 0,this.getHeight()/2 - 4);
		Font font = getFont();
		FontMetrics fm = getFontMetrics(font);
		int sizeChaineG = fm.stringWidth(offre.getArrivee().gare.getNom());
		int heightChaine = fm.getHeight();
		g2.drawString(offre.getArrivee().gare.getNom(), this.getWidth()-sizeChaineG ,this.getHeight()/2-4);
		for(OffreSimple o : offre.getOffres()){
			g2.setColor(couleur[o.getTrain().getId()%7]);
			
			Point2D deb = new Point2D.Double(echelle*(Td.until(o.getDepart().horaire)),height);
			Point2D fin = new Point2D.Double(echelle*(Td.until(o.getArrivee().horaire)),height);
			Line2D l = new Line2D.Double(deb, fin);
			g2.draw(l);
			
			Rectangle2D rect1 = new Rectangle2D.Double(deb.getX(), deb.getY()-3, 6, 6);
			Rectangle2D rect2 = new Rectangle2D.Double(fin.getX()-6, fin.getY()-3, 6, 6);
			g2.fill(rect1);
			g2.fill(rect2);
			
			String str = "" + o.getTrain().getId();
			int sizeChaineT = fm.stringWidth(str);
			g2.drawString(str,(int)Math.min(deb.getX()+2,longeur-sizeChaineT), (int) (height+heightChaine) );
		}
	}
	
	public PanneauOffre (Offre o){
		setLayout(new BoxLayout(this,BoxLayout.LINE_AXIS));
		offre = o;
		this.setPreferredSize(new Dimension(700,50));
	}
}
